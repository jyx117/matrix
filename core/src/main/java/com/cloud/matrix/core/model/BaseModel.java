package com.cloud.matrix.core.model;

import lombok.Data;
import java.util.Date;

/**
 * @author michael
 * @version $ID: BaseModel.java, v0.1 2023-03-01 16:59 michael Exp
 */
@Data
public abstract class BaseModel {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;
}
