package com.cloud.matrix.biz.service.account;

import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.service.enums.account.AccountConfigEnum;
import com.cloud.matrix.service.enums.account.AccountUserType;
import com.cloud.matrix.core.model.account.AccountConfig;
import com.cloud.matrix.core.model.account.AccountUser;
import com.cloud.matrix.core.model.account.RamAccount;
import com.cloud.matrix.core.service.account.AccountConfigService;
import com.cloud.matrix.core.service.account.AccountUserService;
import com.cloud.matrix.core.service.account.RamAccountService;
import com.cloud.matrix.service.api.account.AccountBizService;
import com.cloud.matrix.service.model.account.Account;
import com.cloud.matrix.service.model.account.AccountConfigDetail;
import com.cloud.matrix.service.model.account.AccountUserMapping;
import com.cloud.matrix.service.request.account.QueryAccountsRequest;
import com.cloud.matrix.service.result.ListResult;
import com.cloud.matrix.util.ParamUtil;
import com.cloud.matrix.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author michael
 * @version $Id: AccountBizServiceImpl.java, v 0.1 2023-03-17 5:58 PM Michael Exp $$
 */
@Service
public class AccountBizServiceImpl implements AccountBizService {

    @Autowired
    private AccountUserService   accountUserService;

    @Autowired
    private RamAccountService    ramAccountService;

    @Autowired
    private AccountConfigService accountConfigService;

    private static final List    KEYS = Arrays.asList(
        AccountConfigEnum.ALLOW_LOGIN_CONSOLE.getKey(), AccountConfigEnum.ACCOUNT_TYPE.getKey());

    @Override
    public ListResult<Account> listMyAdminAccounts(QueryAccountsRequest request) {
        Map param = buildParam(request, AccountUserType.ADMIN.getType());
        List<AccountUser> accountUsers = accountUserService.getByCondition(param);
        if (null == accountUsers || accountUsers.size() < 1) {
            return ListResult.empty();
        }
        long total = accountUserService.getCountByCondition(param);

        // 2. 获取账号列表
        List<Account> accounts = patchRamAccounts(accountUsers);
        if (null == accounts || accounts.size() < 1) {
            return ListResult.empty();
        }
        ListResult<Account> result = ListResult.success(request.getPageNum(), request.getPageSize(),
            total, accounts);

        // 3. 账号增加使用人信息
        patchAccountUsers(accounts);

        // 4. 获取账号配置信息
        patchAccountConfigs(accounts);

        return result;
    }

    private List<Account> patchRamAccounts(List<AccountUser> accountUsers) {
        List<String> uidList = accountUsers.stream().map(AccountUser::getAccountUid)
            .collect(Collectors.toList());
        List<RamAccount> ramAccounts = ramAccountService.getByUidListAndProviderUid(uidList,
            accountUsers.get(0).getProviderUid());

        return null == ramAccounts || ramAccounts.size() < 1 ? null
            : ramAccounts.stream().map(item -> {
                return AccountConvertor.INSTANCE.convert2Biz(item);
            }).collect(Collectors.toList());
    }

    private void patchAccountUsers(List<Account> accounts) {
        List<String> uidList = accounts.stream().map(Account::getUid).collect(Collectors.toList());
        List<AccountUser> accountUsers = accountUserService.getByUidListAndType(uidList,
            AccountUserType.USER.getType());
        if (null == accountUsers || accountUsers.size() < 1) {
            return;
        }
        for (AccountUser accountUser : accountUsers) {
            for (Account account : accounts) {
                if (StringUtil.equalsIgnoreCase(account.getUid(), accountUser.getAccountUid())) {
                    List<AccountUserMapping> users = null == account.getUsers() ? new ArrayList<>()
                        : account.getUsers();
                    users.add(AccountConvertor.INSTANCE.convert2Biz(accountUser));
                    account.setUsers(users);
                    break;
                }
            }
        }
    }

    private void patchAccountConfigs(List<Account> accounts) {
        List<String> uidList = accounts.stream().map(Account::getUid).collect(Collectors.toList());
        List<AccountConfig> accountConfigs = accountConfigService.getByUidListAndKeys(uidList,
            KEYS);
        if (null == accountConfigs || accountConfigs.size() < 1) {
            return;
        }
        for (AccountConfig accountConfig : accountConfigs) {
            for (Account account : accounts) {
                if (StringUtil.equalsIgnoreCase(account.getUid(), accountConfig.getAccountUid())) {
                    List<AccountConfigDetail> configs = null == account.getConfigs() ? new ArrayList<>()
                        : account.getConfigs();
                    configs.add(AccountConvertor.INSTANCE.convert2Biz(accountConfig));
                    account.setConfigs(configs);
                    break;
                }
            }
        }
        for (Account account : accounts) {
            Account.patchConfigInfo(account);
        }
    }

    private Map buildParam(QueryAccountsRequest request, String type) {
        Map param = ParamUtil.buildParam(request);
        param.put("userId", CoreContext.getUser());
        param.put("type", type);
        return param;
    }

}
