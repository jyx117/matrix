package com.cloud.matrix.biz.account.controller.request;

import lombok.Data;

/**
 * @author michael
 * @version $Id: SsoLoginRequest.java, v 0.1 2023-03-17 12:02 PM Michael Exp $$
 */
@Data
public class SsoLoginRequest {

    public String uid;

    public String parentUid;

    public String cloudSource;
}
