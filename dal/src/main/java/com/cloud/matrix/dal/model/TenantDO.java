package com.cloud.matrix.dal.model;

import com.cloud.matrix.dal.model.BaseDO;
import lombok.Data;

/**
 * @author michael
 * @version $Id: TenantDO.java, v 0.1 2023-03-02 3:39 PM Michael Exp $$
 */
@Data
public class TenantDO extends BaseDO {

    private String code;

    private String name;
}
