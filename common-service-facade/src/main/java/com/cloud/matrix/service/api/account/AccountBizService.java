package com.cloud.matrix.service.api.account;

import com.cloud.matrix.service.model.account.Account;
import com.cloud.matrix.service.request.account.QueryAccountsRequest;
import com.cloud.matrix.service.result.ListResult;

/**
 * @author michael
 * @version $Id: AccountBizService.java, v 0.1 2023-03-17 5:50 PM Michael Exp $$
 */
public interface AccountBizService {

    /**
     * 查询我管理的子账号列表
     * @param request
     * @return
     */
    public ListResult<Account> listMyAdminAccounts(QueryAccountsRequest request);
}
