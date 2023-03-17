package com.cloud.matrix.core.enums;

import com.cloud.matrix.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: PermissionType.java, v 0.1 2023-03-02 2:50 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum AccountUserType {

    ADMIN("ADMIN", "管理员"),

    USER("USER", "使用人"),;

    private String type;
    private String desc;

    public static AccountUserType find(String type) {
        for (AccountUserType item : values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }
}
