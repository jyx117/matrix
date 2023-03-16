package com.cloud.matrix.core.model.account;

import com.cloud.matrix.core.TenantBaseModel;
import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountBaseModel.java, v 0.1 2023-03-16 2:04 PM Michael Exp $$
 */
@Data
public abstract class AccountBaseModel extends TenantBaseModel {

    /** 主账号uid */
    private String providerUid;
}
