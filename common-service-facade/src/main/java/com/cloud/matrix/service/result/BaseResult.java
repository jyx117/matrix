package com.cloud.matrix.service.result;

import lombok.Data;
import java.io.Serializable;

/**
 * @author michael
 * @version $Id: BaseResult.java, v 0.1 2023-03-02 7:26 PM Michael Exp $$
 */
@Data
public class BaseResult implements Serializable {

    private Boolean success;
    private String  code;
    private String  errorMsg;

    public BaseResult() {
    }

    public BaseResult(Boolean success, String code, String errorMsg) {
        this.success = success;
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static BaseResult error(String code, String errorMsg) {
        return new BaseResult(false, code, errorMsg);
    }

}
