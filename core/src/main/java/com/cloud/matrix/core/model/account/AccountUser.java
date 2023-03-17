package com.cloud.matrix.core.model.account;

import com.cloud.matrix.core.enums.AccountUserType;
import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountUserDO.java, v 0.1 2023-03-17 2:43 PM Michael Exp $$
 */
@Data
public class AccountUser extends AccountBaseModel {

    private String          accountUid;

    private String          userId;

    private AccountUserType type;
}
