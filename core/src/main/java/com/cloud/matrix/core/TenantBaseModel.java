package com.cloud.matrix.core;

import com.cloud.matrix.core.model.BaseModel;
import lombok.Data;

/**
 * @author michael
 * @version $Id: TenantBaseModel.java, v 0.1 2023-03-16 1:45 PM Michael Exp $$
 */
@Data
public abstract class TenantBaseModel extends BaseModel {

    private String tenant;
}
