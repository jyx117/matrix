package com.cloud.matrix.biz.service.account;

import javax.servlet.http.HttpServletResponse;

/**
 * @author michael
 * @version $Id: SsoBizService.java, v 0.1 2023-03-17 11:35 AM Michael Exp $$
 */
public interface SsoBizService {

    /**
     * SSO方式登录
     * @param accountUid
     * @param parentUid
     * @param response
     */
    public void ssoLogin(String accountUid, String parentUid, HttpServletResponse response);

    public void tencentLogin(String accountUid, String parentUid, HttpServletResponse response);
}
