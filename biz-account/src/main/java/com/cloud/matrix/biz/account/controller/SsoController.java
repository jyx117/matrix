package com.cloud.matrix.biz.account.controller;

import com.cloud.matrix.biz.account.controller.request.SsoLoginRequest;
import com.cloud.matrix.biz.account.service.SsoBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author michael
 * @version $Id: SsoController.java, v 0.1 2023-03-17 12:01 PM Michael Exp $$
 */
@RestController
public class SsoController {

    @Autowired
    private SsoBizService ssoBizService;

    @GetMapping(value = "/gateway/account/loginBySso.json")
    public void loginBySso(@Valid SsoLoginRequest request, HttpServletResponse response) {

        ssoBizService.ssoLogin(request.getUid(), request.getParentUid(), response);
    }
}
