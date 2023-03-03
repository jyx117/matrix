package com.cloud.matrix.dal.model.access;

import com.cloud.matrix.dal.model.BaseDO;

import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class UserRoleDO extends BaseDO {

    private String userId;

    private String roleCode;

    private String tenant;

}
