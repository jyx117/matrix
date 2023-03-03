package com.cloud.matrix.core.model.access;

import com.cloud.matrix.core.model.BaseModel;
import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class RolePermission extends BaseModel {

    private String permissionCode;

    private String permissionType;

    private String roleCode;

    private String tenant;
}
