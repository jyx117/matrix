package com.cloud.matrix.dal.model.access;

import com.cloud.matrix.dal.model.BaseDO;

import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class PermissionDO extends BaseDO {

    private String code;

    private String type;

    private String name;
}
