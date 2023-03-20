package com.cloud.matrix.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author michael
 * @version $Id: DataResult.java, v 0.1 2023-03-20 11:37 AM Michael Exp $$
 */
@Data
@NoArgsConstructor
public class DataResult<T> extends BaseResult {

    private T data;

    public DataResult(Boolean success, String code, String errorMsg, T data) {
        super(success, code, errorMsg);
        this.data = data;
    }

    public static <T> DataResult<T> success(T data) {
        return new DataResult<>(true, "success", null, data);
    }
}
