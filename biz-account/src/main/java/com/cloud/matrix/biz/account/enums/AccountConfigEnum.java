package com.cloud.matrix.biz.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: ProviderConfigEnum.java, v 0.1 2023-03-17 3:03 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum AccountConfigEnum {

    LOGIN_TYPE("LOGIN_TYPE", "登录方式"),

    SSO_SAML_DOCUMENT("SSO_SAML_DOCUMENT", "sso登录document文本"),

    SSO_SAML_PUBLIC_KEY("SSO_SAML_PUBLIC_KEY", "sso登录公钥"),

    SSO_SAML_PRIVATE_KEY("SSO_SAML_PRIVATE_KEY", "sso登录私钥"),

    ;

    private String key;
    private String desc;
}
