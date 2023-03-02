package com.cloud.matrix.core.model.access;

import com.cloud.matrix.core.enums.PermissionType;
import com.cloud.matrix.core.model.BaseModel;
import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class Permission extends BaseModel {

    private String         code;

    private PermissionType type;

    private String         name;
}
