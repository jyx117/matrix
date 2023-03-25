package com.cloud.matrix.service.model.account;

import com.cloud.matrix.service.enums.account.AccountUserType;
import lombok.Data;

/**
 * @author michael
 * @version $Id: AccountUserDO.java, v 0.1 2023-03-17 2:43 PM Michael Exp $$
 */
@Data
public class AccountUserMapping extends AccountBase {

    private String          accountUid;

    private String          userId;

    private AccountUserType type;
}
