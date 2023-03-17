package com.cloud.matrix.dal.model.account;

import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountUserDO.java, v 0.1 2023-03-17 2:43 PM Michael Exp $$
 */
@Data
public class AccountUserDO extends AccountBaseDO {

    private String accountUid;

    private String userId;

    private String type;
}
