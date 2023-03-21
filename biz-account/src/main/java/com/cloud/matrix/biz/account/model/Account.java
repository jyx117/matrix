package com.cloud.matrix.biz.account.model;

import com.cloud.matrix.biz.account.enums.AccountConfigEnum;
import com.cloud.matrix.core.model.account.*;
import com.cloud.matrix.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.Objects;

/**
 * @author michael
 * @version $Id: Account.java, v 0.1 2023-03-17 5:50 PM Michael Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends RamAccount {

    private Boolean             allowLoginConsole;

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

    /** 账号配置 */
    private List<AccountConfig> configs;

    public Account(RamAccount account) {
        if (Objects.nonNull(account)) {
            BeanCopier copier = BeanCopier.create(RamAccount.class, Account.class, false);
            copier.copy(account, this, null);
        }
    }

    public static void patchConfigInfo(Account account) {
        if (null != account || null != account.getConfigs()) {
            List<AccountConfig> configs = account.getConfigs();
            for (AccountConfig config : configs) {
                AccountConfigEnum configEnum = AccountConfigEnum.find(config.getK());
                switch (configEnum) {
                    case ALLOW_LOGIN_CONSOLE:
                        account
                            .setAllowLoginConsole(StringUtil.equalsIgnoreCase("1", config.getV()));
                        break;
                }
            }
        }
    }
}
