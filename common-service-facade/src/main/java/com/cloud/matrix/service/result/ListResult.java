package com.cloud.matrix.service.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michael
 * @version $Id: DataResult.java, v 0.1 2023-03-20 11:37 AM Michael Exp $$
 */
@Data
@NoArgsConstructor
public class ListResult<T> extends BaseResult {

    private int     pageNum;

    private int     pageSize;

    private long    total;

    private List<T> data;

    public ListResult(Boolean success, String code, String errorMsg, List<T> data) {
        super(success, code, errorMsg);
        this.data = data;
    }

    public ListResult(Boolean success, String code, String errorMsg, int pageNum, int pageSize,
                      long total, List<T> data) {
        super(success, code, errorMsg);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }

    public static <T> ListResult<T> success(int pageNum, int pageSize, long total, List<T> data) {
        return new ListResult<>(true, "success", null, pageNum, pageSize, total, data);
    }

    public static <T> ListResult<T> empty() {
        return new ListResult<T>(Boolean.TRUE, "SUCCESS", null, 0, 10, 0, new ArrayList<>());
    }
}
