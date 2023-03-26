package com.cloud.matrix.common.exception;

import com.cloud.matrix.common.enums.ErrorCode;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: SystemException.java, v 0.1 2023-03-03 1:45 PM Michael Exp $$
 */
@Getter
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 8312907182931723379L;

    // 错误码
    private String            code;

    // 业务上下文信息
    private String            bizMsg;

    /**
     * 构造一个没有错误信息的 <code>SystemException</code>
     */
    public SystemException() {
        super();
    }

    /**
     * 使用指定的 Throwable 和 Throwable.toString() 作为异常信息来构造 SystemException
     *
     * @param cause 错误原因， 通过 Throwable.getCause() 方法可以获取传入的 cause信息
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用错误信息 message 构造 SystemException
     *
     * @param message 错误信息
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * 使用错误码和错误信息构造 SystemException
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public SystemException(String code, String bizMsg, String message) {
        super(message);
        this.code = code;
        this.bizMsg = bizMsg;
    }

    /**
     * 使用错误信息和 Throwable 构造 SystemException
     *
     * @param message 错误信息
     * @param cause   错误原因
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param code    错误码
     * @param message 错误信息
     * @param cause   错误原因
     */
    public SystemException(String code, String bizMsg, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.bizMsg = bizMsg;
    }

    /**
     * @param errorCode ErrorCode
     */
    public SystemException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * @param errorCode ErrorCode
     * @param cause     错误原因
     */
    public SystemException(ErrorCode errorCode, String bizMsg, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.bizMsg = bizMsg;
    }

    /**
     * @param errorCode ErrorCode
     * @param cause     错误原因
     */
    public SystemException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public String getCode() {
        return code;
    }
}
