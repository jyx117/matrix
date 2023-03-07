package com.cloud.matrix.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author michael
 * @version $Id: UserIdentifyType.java, v 0.1 2023-03-02 2:51 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum UserIdentityType {

    DD("DD", "钉钉"),

    WE_CHAT("WE_CHAT", "微信"),

    PHONE("PHONE", "电话"),

    PASSWORD("PASSWORD", "密码"),

    ;

    private String type;
    private String desc;

    public static UserIdentityType find(String type) {
        for (UserIdentityType item : values()) {
            if (item.getType().equalsIgnoreCase(type)) {
                return item;
            }
        }
        return null;
    }
}
