package com.cloud.matrix.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: PermissionType.java, v 0.1 2023-03-02 2:50 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum PermissionType {

    URL("URL", "页面权限"),

    API("API", "api权限"),

    ;

    private String type;
    private String desc;
}
