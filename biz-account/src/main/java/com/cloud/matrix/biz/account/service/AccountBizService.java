package com.cloud.matrix.biz.account.service;

import com.cloud.matrix.biz.account.model.Account;

import java.util.List;

/**
 * @author michael
 * @version $Id: AccountBizService.java, v 0.1 2023-03-17 5:50 PM Michael Exp $$
 */
public interface AccountBizService {

    public List<Account> listMyAdminAccounts();
}
