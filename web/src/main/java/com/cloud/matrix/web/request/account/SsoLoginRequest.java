package com.cloud.matrix.web.request.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author michael
 * @version $Id: SsoLoginRequest.java, v 0.1 2023-03-17 12:02 PM Michael Exp $$
 */
@Data
public class SsoLoginRequest {

    @NotBlank
    public String uid;

    @NotBlank
    public String parentUid;
}
