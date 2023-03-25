package com.cloud.matrix.service.request.account;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author michael
 * @version $Id: QueryAccountsRequest.java, v 0.1 2023-03-20 11:16 AM Michael Exp $$
 */
@Data
public class QueryAccountsRequest {

    @Min(1)
    int pageNum  = 1;

    @Min(10)
    @Max(100)
    int pageSize = 10;
}
