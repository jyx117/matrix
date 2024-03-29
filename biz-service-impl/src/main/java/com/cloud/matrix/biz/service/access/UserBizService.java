package com.cloud.matrix.biz.service.access;

import com.cloud.matrix.biz.service.access.model.UserDetail;
import com.cloud.matrix.biz.service.access.request.LoginRequest;
import com.cloud.matrix.biz.service.access.request.RegisterUserRequest;

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

    /**
     * 登录
     * @param request
     * @return
     */
    public UserDetail login(LoginRequest request);

    /**
     * 根据userId查询用户详情
     * @param userId
     * @return
     */
    UserDetail queryUserDetail(String userId);
}
