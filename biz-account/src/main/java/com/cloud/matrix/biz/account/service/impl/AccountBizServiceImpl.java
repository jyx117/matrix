package com.cloud.matrix.biz.account.service.impl;

import com.cloud.matrix.biz.account.model.Account;
import com.cloud.matrix.biz.account.service.AccountBizService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author michael
 * @version $Id: AccountBizServiceImpl.java, v 0.1 2023-03-17 5:58 PM Michael Exp $$
 */
@Service
public class AccountBizServiceImpl implements AccountBizService {
    @Override
    public List<Account> listMyAdminAccounts() {
        return null;
    }
}
