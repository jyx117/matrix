package com.cloud.matrix.service.model.account;

import com.cloud.matrix.service.model.TenantBase;
import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountBaseModel.java, v 0.1 2023-03-16 2:04 PM Michael Exp $$
 */
@Data
public abstract class AccountBase extends TenantBase {

    /** 主账号uid */
    private String providerUid;

}
