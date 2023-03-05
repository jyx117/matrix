package com.cloud.matrix.biz.access.service;

import com.cloud.matrix.biz.access.controller.request.RegisterUserRequest;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.enums.UserIdentityType;
import org.springframework.stereotype.Service;

/**
 * @author michael
 * @version $ID: UserBizServiceImpl.java, v0.1 2023-03-05 08:22 michael Exp
 */
@Service
public class UserBizServiceImpl implements UserBizService {
    @Override
    public Long register(RegisterUserRequest request) {
        preCheck(request);

        UserIdentityType userIdentityType = UserIdentityType.find(request.getIdentityType());
        return null;
    }

    private void preCheck(RegisterUserRequest request) {
        if (null == UserIdentityType.find(request.getIdentityType())) {
            throw new BizException(ErrorCode.BIZ_UN_SUPPORT_IDENTITY_TYPE);
        }
    }
}
