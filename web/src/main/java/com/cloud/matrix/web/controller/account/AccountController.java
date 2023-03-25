package com.cloud.matrix.web.controller.account;

import com.cloud.matrix.service.api.account.AccountBizService;
import com.cloud.matrix.service.request.account.QueryAccountsRequest;
import com.cloud.matrix.service.result.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author michael
 * @version $Id: AccountController.java, v 0.1 2023-03-20 4:09 PM Michael Exp $$
 */
@RestController
public class AccountController {

    @Autowired
    private AccountBizService accountBizService;

    @PostMapping(value = "/gateway/account/listMyAdminAccounts.json")
    public ListResult listMyAdminAccounts(@Validated QueryAccountsRequest request,
                                          HttpServletResponse response) {
        return accountBizService.listMyAdminAccounts(request);
    }
}
