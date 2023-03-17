package com.cloud.matrix.biz.account.model;

import com.cloud.matrix.core.model.account.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author michael
 * @version $Id: Account.java, v 0.1 2023-03-17 5:50 PM Michael Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends RamAccount {

    /** 主账号信息 */
    private Provider            provider;

    /** 包含的权限 */
    private List<AccountPolicy> policies;

    /** 已加入的用户组 */
    private List<AccountGroup>  groups;

    /** 管理员 */
    private AccountUser         admin;

    /** 使用者 */
    private List<AccountUser>   users;
}
