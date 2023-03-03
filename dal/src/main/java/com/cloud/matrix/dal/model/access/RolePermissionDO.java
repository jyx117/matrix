package com.cloud.matrix.dal.model.access;

import com.cloud.matrix.dal.model.BaseDO;

import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class RolePermissionDO extends BaseDO {

    private String permissionCode;

    private String permissionType;

    private String roleCode;

    private String tenant;
}
