package com.cloud.matrix.dal.model;

import lombok.Data;

/**
 * @author michael
 * @version $Id: TenantBaseDO.java, v 0.1 2023-03-16 1:44 PM Michael Exp $$
 */
@Data
public abstract class TenantBaseDO extends BaseDO {

    private String tenant;
}
