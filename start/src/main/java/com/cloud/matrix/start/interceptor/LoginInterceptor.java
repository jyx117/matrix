package com.cloud.matrix.start.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.common.result.BaseResult;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.core.service.access.UserAuthService;
import com.cloud.matrix.util.DateUtil;
import com.cloud.matrix.util.JwtUtil;
import com.cloud.matrix.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录拦截器
 * @author michael
 * @version $Id: LoginInterceptor.java, v 0.1 2023-03-03 5:44 PM Michael Exp $$
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserAuthService     userAuthService;

    private static final int    TOKEN_SIZE               = 4;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        // 从http请求头中取出 token
        String token = request.getParameter("token");
        if (StringUtil.isBlank(token)) {
            // 如果为空，则说明没有登录，返回需要跳转登录的response
            writeResponse(BaseResult.error(ErrorCode.BIZ_NOT_LOGIN), response);
            throw new BizException(ErrorCode.BIZ_NOT_LOGIN);
        }
        // 获取token中的userId,identityType,identifier,timeout
        String combineStr;
        try {
            combineStr = JwtUtil.decode(token);
        } catch (JWTDecodeException e) {
            writeResponse(BaseResult.error(ErrorCode.BIZ_LOGIN_EXCEPTION), response);
            throw new BizException(ErrorCode.BIZ_LOGIN_EXCEPTION, token, e);
        }
        String[] combines = combineStr.split("#");
        if (combines.length != TOKEN_SIZE) {
            writeResponse(BaseResult.error(ErrorCode.BIZ_TOKEN_INVALID), response);
            throw new BizException(ErrorCode.BIZ_TOKEN_INVALID);
        }
        String userId = combines[0];
        String identityType = combines[1];
        String identifier = combines[2];
        String timeout = combines[3];
        Long time = DateUtil.DEFAULT_FORMAT2.parse(timeout).getTime();
        if (System.currentTimeMillis() >= time) {
            writeResponse(BaseResult.error(ErrorCode.BIZ_TOKEN_EXPIRED), response);
            throw new BizException(ErrorCode.BIZ_TOKEN_EXPIRED);
        }
        UserAuth userAuth = userAuthService.getByUserId(userId);
        // 用户不存在
        if (null == userAuth) {
            writeResponse(BaseResult.error(ErrorCode.BIZ_USER_NOT_EXIST), response);
            throw new BizException(ErrorCode.BIZ_USER_NOT_EXIST);
        }
        // 验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userAuth.getCredential())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            writeResponse(BaseResult.error(ErrorCode.BIZ_TOKEN_VERIFY_FAILED), response);
            throw new BizException(ErrorCode.BIZ_TOKEN_VERIFY_FAILED, token, e);
        }

        // 设置用户context
        CoreContext.setUser(userId);
        return true;
    }

    private void writeResponse(BaseResult baseResult,
                               HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSON.toJSONString(baseResult));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        CoreContext.cleanUserContext();
    }
}
