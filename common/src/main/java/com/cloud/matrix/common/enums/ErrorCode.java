package com.cloud.matrix.common.enums;

import lombok.Getter;

/**
 * @author michael
 * @version $Id: ErrorCode.java, v 0.1 2023-03-03 1:51 PM Michael Exp $$
 */
@Getter
public enum ErrorCode {

    SYSTEM_COMMON_EXCEPTION("SYS_0001", "系统通用异常"),

    BIZ_COMMON_EXCEPTION("BIZ_0001", "业务通用异常"),

    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "未知异常"),

    GET_PARAM_NOT_VALID("GET_PARAM_NOT_VALID", "get请求参数异常"),

    REQUEST_PARAM_NOT_VALID("REQUEST_PARAM_NOT_VALID", "请求参数异常"),

    POST_PARAM_NOT_VALID("POST_PARAM_NOT_VALID", "post请求参数异常"),

    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
