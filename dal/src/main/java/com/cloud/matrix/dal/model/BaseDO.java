package com.cloud.matrix.dal.model;

import lombok.Data;

import java.util.Date;

/**
 * @author michael
 * @version $ID: BaseDO.java, v0.1 2023-03-01 16:48 michael Exp
 */
@Data
public abstract class BaseDO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;
}
