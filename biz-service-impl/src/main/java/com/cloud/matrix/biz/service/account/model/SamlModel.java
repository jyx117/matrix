package com.cloud.matrix.biz.service.account.model;

import lombok.Data;

/**
 * @author michael
 * @version $Id: SamlModel.java, v 0.1 2023-03-17 11:39 AM Michael Exp $$
 */
@Data
public class SamlModel {

    private String publicKey;

    private String privateKey;

    private String saml;
}
