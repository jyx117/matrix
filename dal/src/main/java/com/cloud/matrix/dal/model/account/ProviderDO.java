package com.cloud.matrix.dal.model.account;

import com.cloud.matrix.dal.model.TenantBaseDO;
import lombok.Data;

/**
 * @author michael
 * @version $Id: Provider.java, v 0.1 2023-03-16 1:42 PM Michael Exp $$
 */
@Data
public class ProviderDO extends TenantBaseDO {

    private String uid;

    private String name;

    private String cloudSource;

}
