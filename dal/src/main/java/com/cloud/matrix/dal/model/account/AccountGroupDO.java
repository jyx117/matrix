package com.cloud.matrix.dal.model.account;

import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class AccountGroupDO extends AccountBaseDO {

    /** 子账号uid */
    private String accountUid;

    /** 用户组名 */
    private String groupName;

}
