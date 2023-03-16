package com.cloud.matrix.core.model.account;

import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class RolePolicy extends AccountBaseModel {

    /** 权限名 */
    private String policyName;

    /** 角色名 */
    private String roleName;

}
