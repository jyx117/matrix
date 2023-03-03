package com.cloud.matrix.start;

import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.common.exception.SystemException;
import com.cloud.matrix.common.result.BaseResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author michael
 * @version $Id: GlobalExceptionHandler.java, v 0.1 2023-03-03 2:01 PM Michael Exp $$
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 业务异常处理
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseResult bizErrorHandler(HttpServletRequest request, BizException e) {
        return BaseResult.error(e.getCode(), e.getMessage());
    }

    // 系统异常处理
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseResult sysErrorHandler(HttpServletRequest request, SystemException e) {
        return BaseResult.error(ErrorCode.SYSTEM_COMMON_EXCEPTION.getCode(),
            ErrorCode.SYSTEM_COMMON_EXCEPTION.getMessage());
    }

    // 未捕获异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult defaultErrorHandler(HttpServletRequest request, Exception e) {
        return BaseResult.error(ErrorCode.UNKNOWN_EXCEPTION.getCode(),
            ErrorCode.UNKNOWN_EXCEPTION.getMessage());
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResult bindExceptionHandler(HttpServletRequest request, BindException e) {
        return BaseResult.error(ErrorCode.GET_PARAM_NOT_VALID.getCode(),
            ErrorCode.GET_PARAM_NOT_VALID.getMessage());
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public BaseResult constraintViolationExceptionHandler(HttpServletRequest request,
                                                          ConstraintViolationException e) {
        String collect = e.getConstraintViolations().stream().filter(Objects::nonNull)
            .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
            .collect(Collectors.joining(", "));
        return BaseResult.error(ErrorCode.REQUEST_PARAM_NOT_VALID.getCode(),
            ErrorCode.REQUEST_PARAM_NOT_VALID.getMessage() + ":" + collect);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult methodArgumentNotValidExceptionHandler(HttpServletRequest request,
                                                             MethodArgumentNotValidException e) {
        if (e.getBindingResult() != null
            && !CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())) {
            return BaseResult
                .error(ErrorCode.POST_PARAM_NOT_VALID
                    .getCode(),
                    ErrorCode.POST_PARAM_NOT_VALID.getMessage() + ":" + e.getBindingResult()
                        .getAllErrors().get(0).getDefaultMessage());
        } else {
            return BaseResult.error(ErrorCode.POST_PARAM_NOT_VALID.getCode(),
                ErrorCode.POST_PARAM_NOT_VALID.getMessage() + ":" + e.getMessage());
        }
    }
}
