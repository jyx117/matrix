package com.cloud.matrix.service.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author michael
 * @version $ID: TenantBase.java, v0.1 2023-03-25 09:46 michael Exp
 */
@Data
public abstract class TenantBase implements Serializable {

    private String tenant;
}
