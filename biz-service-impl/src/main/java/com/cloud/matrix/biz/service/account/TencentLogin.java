package com.cloud.matrix.biz.service.account;

import com.cloud.matrix.biz.service.account.model.SamlModel;
import com.cloud.matrix.core.model.account.AccountConfig;
import com.cloud.matrix.service.enums.account.AccountConfigEnum;
import com.cloud.matrix.service.enums.account.ProviderSite;
import com.cloud.matrix.util.LogUtil;
import com.cloud.matrix.util.OpenSAMLUtils;
import com.cloud.matrix.util.StringUtil;
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
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author michael
 * @version $ID: TencentLogin.java, v0.1 2023-03-27 19:37 michael Exp
 */
@Component
public class TencentLogin {

    private String              responseUrl   = "https://cloud.tencent.com/saml/sso";

    // private String audienceUri = "https://cloud.tencent.com/100020280319/saml/sso";
    private String              audienceUri   = "https://cloud.tencent.com/100020280319/saml/sso";

    private String              relayState    = "https://cloud.tencent.com";

    private static final String TEMPLATE_PATH = "/templates/saml2-post-binding.vm";

    // private static final String   ENTITY_ID     = "https://cloud.tencent.com/100020280319/saml/sso";
    private static final String ENTITY_ID     = "https://paas.cmatrix.net/accounts/idp/ssoEntity";

    public void tencentLogin(String accountUid, String parentUid, HttpServletResponse response) {
        // 5. 加载saml配置
        SamlModel samlModel = getSamlConfig(accountUid, parentUid);
        // 6. 处理请求
        handleResponse(responseUrl, relayState, parentUid, "tencent1", audienceUri, samlModel,
            response);
    }

    /**
     * 获取saml config
     * @return
     */
    private SamlModel getSamlConfig(String accountUid, String providerUid) {
        SamlModel samlModel = new SamlModel();
        samlModel.setSaml(document);
        samlModel.setPrivateKey(privateKey);
        samlModel.setPublicKey(publicKey);
        return samlModel;
    }

    private void handleResponse(String responseUrl, String relayState, String parentUid,
                                String userPrincipalName, String audienceUri, SamlModel samlModel,
                                HttpServletResponse response) {
        Response artifactResponse = buildArtifactResponse(responseUrl, parentUid, userPrincipalName,
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

    private Response buildArtifactResponse(String responseUrl, String parentUid,
                                           String userPrincipalName, String audienceUri,
                                           SamlModel samlModel) {
        Response artifactResponse = OpenSAMLUtils.buildSAMLObject(Response.class);
        Issuer issuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        issuer.setValue(ENTITY_ID);
        artifactResponse.setIssuer(issuer);
        artifactResponse.setIssueInstant(new DateTime());
        artifactResponse.setInResponseTo(null);
        artifactResponse.setDestination(responseUrl);
        artifactResponse.setID(OpenSAMLUtils.getTencentRandomId(parentUid, userPrincipalName));

        Status status = OpenSAMLUtils.buildSAMLObject(Status.class); //构建响应状态
        StatusCode statusCode = OpenSAMLUtils.buildSAMLObject(StatusCode.class);
        statusCode.setValue(StatusCode.SUCCESS_URI);
        status.setStatusCode(statusCode);
        artifactResponse.setStatus(status);

        Assertion assertion = buildAssertion(responseUrl, parentUid, userPrincipalName, audienceUri,
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
    private Assertion buildAssertion(String responseUrl, String parentUid, String userPrincipalName,
                                     String audienceUri, SamlModel samlModel) {
        Assertion assertion = OpenSAMLUtils.buildSAMLObject(Assertion.class);
        Issuer issuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        issuer.setValue(ENTITY_ID);
        assertion.setIssuer(issuer);
        assertion.setIssueInstant(new DateTime());
        assertion.setID(OpenSAMLUtils.getTencentRandomId(parentUid, userPrincipalName));

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

    private String privateKey = "";

    private String publicKey  = "";

    private String document   = "";
}
