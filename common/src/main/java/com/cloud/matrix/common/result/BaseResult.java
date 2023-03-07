package com.cloud.matrix.common.result;

import com.cloud.matrix.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author michael
 * @version $Id: BaseResult.java, v 0.1 2023-03-02 7:26 PM Michael Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    private Boolean success;
    private String  code;
    private String  errorMsg;
    private T       data;

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(true, "success", null, data);
    }

    public static <T> BaseResult<T> error(String code, String errorMsg) {
        return new BaseResult<>(false, code, errorMsg, null);
    }

    public static <T> BaseResult<T> error(ErrorCode errorCode) {
        return new BaseResult<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }
}
