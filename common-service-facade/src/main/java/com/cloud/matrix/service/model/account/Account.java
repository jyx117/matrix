package com.cloud.matrix.service.model.account;

import com.cloud.matrix.service.enums.account.AccountConfigEnum;
import com.cloud.matrix.service.enums.account.AccountType;
import com.cloud.matrix.service.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * @author michael
 * @version $Id: Account.java, v 0.1 2023-03-17 5:50 PM Michael Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AccountBase {

    /** 子账号uid */
    private String                     uid;

    /** 登录名截取前一段 */
    private String                     name;

    /** 登录名，唯一键 */
    private String                     loginName;

    /** 显示名 */
    private String                     showName;

    /** 备注 */
    private String                     comment;

    /** 账号创建时间 */
    private Date                       createTime;

    /** 电话 */
    private String                     phone;

    /** 邮件 */
    private String                     mail;

    /** 是否允许登录控制台 */
    private Boolean                    allowLoginConsole;

    /** 账号类型 */
    private AccountType                accountType;

    /** 主账号信息 */
    private ProviderDetail             provider;

    /** 包含的权限 */
    private List<AccountPolicyMapping> policies;

    /** 已加入的用户组 */
    private List<AccountGroupMapping>  groups;

    /** 管理员 */
    private AccountUserMapping         admin;

    /** 使用者 */
    private List<AccountUserMapping>   users;

    /** 账号配置 */
    private List<AccountConfigDetail>  configs;

    public static void patchConfigInfo(Account account) {
        if (null != account || null != account.getConfigs()) {
            List<AccountConfigDetail> configs = account.getConfigs();
            for (AccountConfigDetail config : configs) {
                AccountConfigEnum configEnum = AccountConfigEnum.find(config.getK());
                switch (configEnum) {
                    case ALLOW_LOGIN_CONSOLE:
                        account
                            .setAllowLoginConsole(StringUtil.equalsIgnoreCase("1", config.getV()));
                        break;
                    case ACCOUNT_TYPE:
                        account.setAccountType(AccountType.find(config.getV()));
                        break;
                }
            }
        }
    }
}
