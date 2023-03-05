package com.cloud.matrix.biz.access.service;

import com.cloud.matrix.biz.access.controller.request.RegisterUserRequest;

/**
 * @author michael
 * @version $ID: UserService.java, v0.1 2023-03-04 09:04 michael Exp
 */
public interface UserBizService {

    /**
     * 注册用户
     * @param request
     * @return
     */
    public Long register(RegisterUserRequest request);
}
