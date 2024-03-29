package com.cloud.matrix.web.controller.access;

import com.cloud.matrix.biz.service.access.UserBizService;
import com.cloud.matrix.biz.service.access.request.LoginRequest;
import com.cloud.matrix.biz.service.access.request.RegisterUserRequest;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.service.result.DataResult;
import com.cloud.matrix.util.CryptUtil;
import com.cloud.matrix.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 * @version $Id: LoginController.java, v 0.1 2023-03-07 4:13 PM Michael Exp $$
 */
@RestController
public class LoginController {

    @Autowired
    private UserBizService userBizService;

    @PostMapping(value = "/gateway/access/register.json")
    public DataResult register(@RequestBody @Validated RegisterUserRequest request) {
        preCheck(request);

        return DataResult.success(userBizService.register(request));
    }

    private void preCheck(RegisterUserRequest request) {
        UserIdentityType identityType = UserIdentityType.find(request.getIdentityType());
        if (null == identityType) {
            throw new BizException(ErrorCode.BIZ_UN_SUPPORT_IDENTITY_TYPE);
        }

        switch (identityType) {
            case PASSWORD:
                if (StringUtil.isBlank(request.getCredential())) {
                    throw new BizException(ErrorCode.BIZ_CREDENTIAL_NOT_BLANK);
                }
                String password = CryptUtil.encodePassword(request.getCredential());
                request.setCredential(password);
                request.setIdentifier(request.getName());
                break;
        }
    }

    @PostMapping(value = "/gateway/access/login.json")
    public DataResult passwordRegister(@RequestBody LoginRequest request) {
        return DataResult.success(userBizService.login(request));
    }
}
