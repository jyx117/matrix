package com.cloud.matrix.dal.model.access;

import com.cloud.matrix.dal.model.BaseDO;
import lombok.Data;

/**
 * @author michael
 * @version $Id: Role.java, v 0.1 2023-03-02 4:31 PM Michael Exp $$
 */
@Data
public class RoleDO extends BaseDO {

    private String code;

    private String description;

    private String name;

    private String tenant;
}
