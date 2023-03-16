package com.cloud.matrix.dal.model.account;

import com.cloud.matrix.dal.model.TenantBaseDO;
import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountBaseDO.java, v 0.1 2023-03-16 1:54 PM Michael Exp $$
 */
@Data
public class AccountBaseDO extends TenantBaseDO {

    /** 主账号uid */
    private String providerUid;
}
