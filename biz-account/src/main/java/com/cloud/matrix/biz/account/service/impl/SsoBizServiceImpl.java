package com.cloud.matrix.biz.account.service.impl;

import com.cloud.matrix.biz.account.enums.AccountConfigEnum;
import com.cloud.matrix.biz.account.enums.ProviderConfigEnum;
import com.cloud.matrix.biz.account.enums.ProviderSite;
import com.cloud.matrix.biz.account.service.SsoBizService;
import com.cloud.matrix.biz.account.model.SamlModel;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.account.*;
import com.cloud.matrix.core.service.account.*;
import com.cloud.matrix.util.LogUtil;
import com.cloud.matrix.util.OpenSAMLUtils;
import com.cloud.matrix.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.velocity.app.VelocityEngine;
import org.apache.xml.security.Init;
import org.joda.time.DateTime;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.binding.encoding.HTTPPostEncoder;
import org.opensaml.saml2.core.*;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.Endpoint;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.x509.X509Credential;
import org.opensaml.xml.signature.*;

/**
 * @author michael
 * @version $Id: SsoBizServiceImpl.java, v 0.1 2023-03-17 11:36 AM Michael Exp $$
 */
@Service
public class SsoBizServiceImpl implements SsoBizService {

    private static final Logger   logger        = LoggerFactory.getLogger(SsoBizServiceImpl.class);

    /** audienceUri start */
    // 中国站
    @Value("${sso.alibaba.chinaAudienceUriTemplate}")
    String                        chinaAudienceUriTemplate;
    // 国际站
    @Value("${sso.alibaba.overseaAudienceUriTemplate}")
    String                        overseaAudienceUriTemplate;
    /** audienceUri end   */

    /** relayState start */
    // 中国站 callback url
    @Value("${sso.alibaba.chinaRelayState}")
    String                        chinaRelayState;
    // 国际站 callback url
    @Value("${sso.alibaba.overseaRelayState}")
    String                        overseaRelayState;
    /** relayState end   */

    /** sp 验证中心url start */
    // 中国站 sp 验证中心url
    @Value("${sso.alibaba.chinaResponseUrl}")
    String                        chinaResponseUrl;
    // 国际站 sp 验证中心url
    @Value("${sso.alibaba.overseaResponseUrl}")
    String                        overseaResponseUrl;
    /** sp 验证中心url end   */

    // 现在无用
    private static final String   RESPONSE_ID   = null;
    private static final String   TEMPLATE_PATH = "/templates/saml2-post-binding.vm";
    private static final String   ENTITY_ID     = "https://cloudmatrix.alibaba-inc.com/accounts/idp/sso";

    @Autowired
    private AccountUserService    accountUserService;

    @Autowired
    private ProviderService       providerService;

    @Autowired
    private RamAccountService     ramAccountService;

    @Autowired
    private ProviderConfigService providerConfigService;

    @Autowired
    private AccountConfigService  accountConfigService;

    @Override
    public void ssoLogin(String accountUid, String parentUid, HttpServletResponse response) {
        // 1. 前置检查
        RamAccount ramAccount = ramAccountService.getByUid(accountUid, parentUid);
        preCheck(accountUid, parentUid, ramAccount);

        ProviderConfig providerConfig = providerConfigService.getUnique(parentUid,
            ProviderConfigEnum.SITE.getKey());
        if (null == providerConfig) {
            throw new BizException(ErrorCode.BIZ_ACCOUNT_PROVIDER_HAS_NO_SITE, parentUid);
        }
        ProviderSite site = ProviderSite.find(providerConfig.getV());

        // 2. 设置audienceUri
        String audienceUri = parseAudienceUri(site, parentUid);
        LogUtil.info(logger, "[SsoComponent.loginBySso][audienceUri=", audienceUri, "]");

        // 3. 设置responseUrl
        String responseUrl = parseResponseUrl(site);
        LogUtil.info(logger, "[SsoComponent.loginBySso][responseUrl=", responseUrl, "]");

        // 4. 设置relayState
        String relayState = parseRelayState(site);
        LogUtil.info(logger, "[SsoComponent.loginBySso][relayState=", relayState, "]");

        // 5. 加载saml配置
        SamlModel samlModel = getSamlConfig(accountUid, parentUid);
        LogUtil.info(logger, "[SsoComponent.loginBySso][samlModel=",
            samlModel == null ? false : true, "]");
        if (null == samlModel) {
            return;
        }

        // 6. 处理请求
        handleResponse(responseUrl, relayState, ramAccount.getLoginName(), audienceUri, samlModel,
            response);
    }

    private void preCheck(String accountUid, String providerUid, RamAccount ramAccount) {
        // 1. 主账号是否存在
        Provider provider = providerService.getUnique(providerUid);
        if (null == provider) {
            throw new BizException(ErrorCode.BIZ_ACCOUNT_PROVIDER_UID_NOT_EXIST, providerUid);
        }

        // 2. 云类型是否支持
        if (null == provider.getCloudSource()) {
            throw new BizException(ErrorCode.BIZ_ACCOUNT_NO_SUPPORT_CLOUD_SOURCE,
                provider.getCloudSource().getType());
        }

        // 2. 子账号是否存在
        if (null == ramAccount) {
            throw new BizException(ErrorCode.BIZ_ACCOUNT_ACCOUNT_UID_NOT_EXIST, accountUid);
        }

        // 3. 是否有权限
        AccountUser accountUser = accountUserService.getUnique(CoreContext.getUser(), accountUid,
            providerUid);
        if (null == accountUser) {
            throw new BizException(ErrorCode.BIZ_ACCOUNT_NO_ACCOUNT_AUTH, accountUid);
        }
    }

    /**
     * 获取saml config
     * @return
     */
    private SamlModel getSamlConfig(String accountUid, String providerUid) {
        AccountConfig document = accountConfigService.getUnique(accountUid, providerUid,
            AccountConfigEnum.SSO_SAML_DOCUMENT.getKey());
        AccountConfig publicKey = accountConfigService.getUnique(accountUid, providerUid,
            AccountConfigEnum.SSO_SAML_PUBLIC_KEY.getKey());
        AccountConfig privateKey = accountConfigService.getUnique(accountUid, providerUid,
            AccountConfigEnum.SSO_SAML_PRIVATE_KEY.getKey());

        SamlModel samlModel = new SamlModel();
        samlModel.setSaml(document.getV());
        samlModel.setPrivateKey(privateKey.getV());
        samlModel.setPublicKey(publicKey.getV());
        return samlModel;
    }

    private void handleResponse(String responseUrl, String relayState, String userPrincipalName,
                                String audienceUri, SamlModel samlModel,
                                HttpServletResponse response) {
        Response artifactResponse = buildArtifactResponse(responseUrl, userPrincipalName,
            audienceUri, samlModel);

        BasicSAMLMessageContext context = new BasicSAMLMessageContext<>();
        Endpoint endpoint = OpenSAMLUtils.buildSAMLObject(AssertionConsumerService.class);

        endpoint.setResponseLocation(responseUrl);
        HttpServletResponseAdapter outTransport = new HttpServletResponseAdapter(response, false);
        context.setOutboundMessageTransport(outTransport);
        context.setPeerEntityEndpoint(endpoint);
        context.setOutboundSAMLMessage(artifactResponse);
        context.setRelayState(relayState);

        VelocityEngine velocityEngine = OpenSAMLUtils.getVelocityEngine();
        //http-post方式响应
        HTTPPostEncoder encoder = new HTTPPostEncoder(velocityEngine, TEMPLATE_PATH);
        try {
            encoder.encode(context);
        } catch (MessageEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private Response buildArtifactResponse(String responseUrl, String userPrincipalName,
                                           String audienceUri, SamlModel samlModel) {
        Response artifactResponse = OpenSAMLUtils.buildSAMLObject(Response.class);
        Issuer issuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        issuer.setValue(ENTITY_ID);
        artifactResponse.setIssuer(issuer);
        artifactResponse.setIssueInstant(new DateTime());
        artifactResponse.setInResponseTo(RESPONSE_ID);
        artifactResponse.setDestination(responseUrl);
        artifactResponse.setID(OpenSAMLUtils.getRandomId());

        Status status = OpenSAMLUtils.buildSAMLObject(Status.class); //构建响应状态
        StatusCode statusCode = OpenSAMLUtils.buildSAMLObject(StatusCode.class);
        statusCode.setValue(StatusCode.SUCCESS_URI);
        status.setStatusCode(statusCode);
        artifactResponse.setStatus(status);

        Assertion assertion = buildAssertion(responseUrl, userPrincipalName, audienceUri,
            samlModel); //构建断言
        artifactResponse.getAssertions().add(assertion);

        return artifactResponse;
    }

    /**
     * 设置断言
     * @param responseUrl
     * @param userPrincipalName
     * @param audienceUri
     * @param samlModel
     * @return
     */
    private Assertion buildAssertion(String responseUrl, String userPrincipalName,
                                     String audienceUri, SamlModel samlModel) {
        Assertion assertion = OpenSAMLUtils.buildSAMLObject(Assertion.class);
        Issuer issuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        issuer.setValue(ENTITY_ID);
        assertion.setIssuer(issuer);
        assertion.setIssueInstant(new DateTime());
        assertion.setID(OpenSAMLUtils.getRandomId());

        Subject subject = OpenSAMLUtils.buildSAMLObject(Subject.class); //构建Subject
        assertion.setSubject(subject);

        NameID nameID = OpenSAMLUtils.buildSAMLObject(NameID.class);
        nameID.setFormat(NameIDType.TRANSIENT);
        nameID.setValue(userPrincipalName);
        subject.setNameID(nameID);

        subject.getSubjectConfirmations().add(buildSubjectConfirmation(responseUrl));

        assertion.setConditions(buildConditions(audienceUri));
        assertion.getAuthnStatements().add(buildAuthnStatement());

        // 加载公私钥，使用私钥签名
        try {
            Credential credential = OpenSAMLUtils.getSigningCredential(samlModel.getPublicKey(),
                samlModel.getPrivateKey());
            signAssertion(assertion, (X509Credential) credential);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assertion;
    }

    /**
     * 实现私钥签名
     * @param assertion
     * @param signingCredential
     */
    private void signAssertion(Assertion assertion, X509Credential signingCredential) {
        Signature signature = OpenSAMLUtils.buildSAMLObject(Signature.class);
        signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
        signature.setSigningCredential(signingCredential);
        try {
            //x.509证书
            KeyInfo keyInfo = OpenSAMLUtils.buildSAMLObject(KeyInfo.class);
            org.opensaml.xml.signature.X509Data data = OpenSAMLUtils
                .buildSAMLObject(org.opensaml.xml.signature.X509Data.class);
            X509Certificate cert = OpenSAMLUtils.buildSAMLObject(X509Certificate.class);
            String value = Base64.getEncoder()
                .encodeToString(signingCredential.getEntityCertificate().getEncoded());
            cert.setValue(value);

            data.getX509Certificates().add(cert);
            keyInfo.getX509Datas().add(data);
            signature.setKeyInfo(keyInfo);
            signingCredential.getEntityCertificate().getSignature();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertion.setSignature(signature); //断言签名
        List<Signature> signatures = new ArrayList<Signature>();
        signatures.add(signature);

        MarshallerFactory marshallerFactory = OpenSAMLUtils.getMarshallerFactory();
        try {
            marshallerFactory.getMarshaller(assertion).marshall(assertion);
        } catch (MarshallingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            Init.init();
            Signer.signObjects(signatures);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建实现subject确认信息
     * @param responseUrl
     * @return
     */
    private SubjectConfirmation buildSubjectConfirmation(String responseUrl) {
        SubjectConfirmation subjectConfirmation = OpenSAMLUtils
            .buildSAMLObject(SubjectConfirmation.class);
        subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);

        SubjectConfirmationData subjectConfirmationData = OpenSAMLUtils
            .buildSAMLObject(SubjectConfirmationData.class);
        subjectConfirmationData.setInResponseTo(null);

        subjectConfirmationData.setNotOnOrAfter(new DateTime().plusDays(2));
        subjectConfirmationData.setRecipient(responseUrl);

        subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);

        return subjectConfirmation;
    }

    /**
     * 创建conditions
     * @param audienceUri
     * @return
     */
    private Conditions buildConditions(String audienceUri) {
        Conditions conditions = OpenSAMLUtils.buildSAMLObject(Conditions.class);
        conditions.setNotBefore(new DateTime().minusDays(2));
        conditions.setNotOnOrAfter(new DateTime().plusDays(2));
        AudienceRestriction audienceRestriction = OpenSAMLUtils
            .buildSAMLObject(AudienceRestriction.class);
        Audience audience = OpenSAMLUtils.buildSAMLObject(Audience.class);
        audience.setAudienceURI(audienceUri);
        audienceRestriction.getAudiences().add(audience);
        conditions.getAudienceRestrictions().add(audienceRestriction);
        return conditions;
    }

    /**
     * 创建authStatement
     * @return
     */
    private AuthnStatement buildAuthnStatement() {
        AuthnStatement authnStatement = OpenSAMLUtils.buildSAMLObject(AuthnStatement.class);
        AuthnContext authnContext = OpenSAMLUtils.buildSAMLObject(AuthnContext.class);
        AuthnContextClassRef authnContextClassRef = OpenSAMLUtils
            .buildSAMLObject(AuthnContextClassRef.class);
        authnContextClassRef.setAuthnContextClassRef(AuthnContext.SMARTCARD_AUTHN_CTX);
        authnContext.setAuthnContextClassRef(authnContextClassRef);
        authnStatement.setAuthnContext(authnContext);

        authnStatement.setAuthnInstant(new DateTime());

        return authnStatement;
    }

    /**
     * 设置audienceUri
     * @param site
     * @param parentUid
     * @return
     */
    private String parseAudienceUri(ProviderSite site, String parentUid) {
        if (site == ProviderSite.CHINA) {
            return StringUtil.replace(chinaAudienceUriTemplate, "parentUid", parentUid);
        } else if (site == ProviderSite.OVERSEA) {
            return StringUtil.replace(overseaAudienceUriTemplate, "parentUid", parentUid);
        }
        return null;
    }

    /**
     * 设置responseUrl
     * @param site
     * @return
     */
    private String parseResponseUrl(ProviderSite site) {
        if (site == ProviderSite.CHINA) {
            return chinaResponseUrl;
        } else if (site == ProviderSite.OVERSEA) {
            return overseaResponseUrl;
        }
        return null;
    }

    /**
     * 设置relayState
     * @param site
     * @return
     */
    private String parseRelayState(ProviderSite site) {
        if (site == ProviderSite.CHINA) {
            return chinaRelayState;
        } else if (site == ProviderSite.OVERSEA) {
            return overseaRelayState;
        }
        return null;
    }
}
