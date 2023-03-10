package com.cloud.matrix.core.model.access;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.cloud.matrix.core.model.BaseModel;

/**
 * @author michael
 * @version $ID: User.java, v0.1 2023-03-01 17:00 michael Exp
 */
@Data
@AllArgsConstructor
public class User extends BaseModel {

    private String userId;

    private String name;

    private String avatar;
}
