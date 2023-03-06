package com.cloud.matrix.biz.access.service;

import com.cloud.matrix.biz.access.controller.request.RegisterUserRequest;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.core.service.access.UserAuthService;
import com.cloud.matrix.core.service.access.UserService;
import com.cloud.matrix.util.IdGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author michael
 * @version $ID: UserBizServiceImpl.java, v0.1 2023-03-05 08:22 michael Exp
 */
@Service
public class UserBizServiceImpl implements UserBizService {

    @Autowired
    private UserService     userService;

    @Autowired
    private UserAuthService userAuthService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long register(RegisterUserRequest request) {
        preCheck(request);

        UserIdentityType userIdentityType = UserIdentityType.find(request.getIdentityType());
        String userId = buildUserId(userIdentityType);
        // 1. add user auth
        UserAuth userAuth = new UserAuth(userId, request.getIdentifier(), userIdentityType,
            request.getCredential());
        userAuthService.add(userAuth);

        // 2. add user
        User user = new User(userId, request.getName(), request.getAvatar());
        userService.add(user);

        return userAuth.getId();
    }

    private String buildUserId(UserIdentityType userIdentityType) {
        switch (userIdentityType) {
            case DD:
                return IdGenerateUtil.generateUserDdId();
            case WE_CHAT:
                return IdGenerateUtil.generateUserWechatId();
            case PHONE:
                return IdGenerateUtil.generateUserPhoneId();
            default:
                return IdGenerateUtil.generateUserId();
        }
    }

    private void preCheck(RegisterUserRequest request) {
        if (null == UserIdentityType.find(request.getIdentityType())) {
            throw new BizException(ErrorCode.BIZ_UN_SUPPORT_IDENTITY_TYPE);
        }
    }
}
