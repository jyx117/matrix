package com.cloud.matrix.biz.access.service;

import com.cloud.matrix.biz.access.controller.request.LoginRequest;
import com.cloud.matrix.biz.access.controller.request.RegisterUserRequest;
import com.cloud.matrix.biz.access.model.UserDetail;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.core.model.access.UserRole;
import com.cloud.matrix.core.service.access.UserAuthService;
import com.cloud.matrix.core.service.access.UserRoleService;
import com.cloud.matrix.core.service.access.UserService;
import com.cloud.matrix.util.CryptUtil;
import com.cloud.matrix.util.IdGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    @Autowired
    private UserRoleService userRoleService;

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

    @Override
    public UserDetail login(LoginRequest request) {
        UserAuth userAuth = userAuthService.getByIdentity(request.getName(), UserIdentityType.PASSWORD.name());
        if (null == userAuth) {
            throw new BizException(ErrorCode.BIZ_USER_NOT_EXIST);
        }
        if (!CryptUtil.matches(request.getCredential(), userAuth.getCredential())) {
            throw new BizException(ErrorCode.BIZ_PASSWORD_ERROR);
        }
        return queryUserDetail(userAuth.getUserId());
    }

    @Override
    public UserDetail queryUserDetail(String userId) {
        // 1. 查询用户基本信息
        User user = userService.getByUserId(userId);
        UserDetail userDetail = new UserDetail();
        UserDetail.patchUser(userDetail, user);

        // 2. 查询auth
        UserAuth userAuth = userAuthService.getByUserId(userId);
        UserDetail.patchUserAuth(userDetail, userAuth);

        // 3. 查询角色
        List<UserRole> roles = userRoleService.getByUserId(userId);
        userDetail.setRoles(roles);
        List<String> tenants = roles == null || roles.size() < 1 ? null
            : roles.stream().map(item -> {
                return item.getTenant();
            }).distinct().collect(Collectors.toList());
        userDetail.setTenants(tenants);
        return userDetail;
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
