package com.cloud.matrix.common.enums;

import lombok.Getter;

/**
 * @author michael
 * @version $Id: ErrorCode.java, v 0.1 2023-03-03 1:51 PM Michael Exp $$
 */
@Getter
public enum ErrorCode {

    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "未知异常"),

    GET_PARAM_NOT_VALID("GET_PARAM_NOT_VALID", "get请求参数异常"),

    REQUEST_PARAM_NOT_VALID("REQUEST_PARAM_NOT_VALID", "请求参数异常"),

    POST_PARAM_NOT_VALID("POST_PARAM_NOT_VALID", "post请求参数异常"),

    /** 系统异常start */
    SYSTEM_COMMON_EXCEPTION("SYS_0001", "系统通用异常"),
    /** 系统异常end */

    /** 业务异常start */
    BIZ_COMMON_EXCEPTION("BIZ_0001", "业务通用异常"),

    BIZ_UN_SUPPORT_IDENTITY_TYPE("BIZ_0002", "不支持的用户注册类型"),

    BIZ_NOT_LOGIN("BIZ_0003", "用户未登录"),

    BIZ_LOGIN_EXCEPTION("BIZ_0004", "登录异常"),

    BIZ_TOKEN_INVALID("BIZ_0005", "用户登录token不合法"),

    BIZ_TOKEN_EXPIRED("BIZ_0006", "token过期"),

    BIZ_USER_NOT_EXIST("BIZ_0007", "用户不存在"),

    BIZ_TOKEN_VERIFY_FAILED("BIZ_0008", "token校验失败"),

    BIZ_CREDENTIAL_NOT_BLANK("BIZ_0009", "用户密码不能为空"),

    BIZ_PASSWORD_ERROR("BIZ_0010", "用户密码错误"),


    /** 业务异常end */

    ;

    private String  code;
    private String  message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
