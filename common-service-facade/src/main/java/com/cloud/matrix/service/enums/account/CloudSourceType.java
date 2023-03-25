package com.cloud.matrix.service.enums.account;

import com.cloud.matrix.service.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: PermissionType.java, v 0.1 2023-03-02 2:50 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum CloudSourceType {

    ALIBABA("ALIBABA", "阿里云"),

    TENCENT("TENCENT", "腾讯云"),

    HUAWEI("HUAWEI", "华为云"),

    AWS("AWS", "AWS"),

    GOOGLE("GOOGLE", "谷歌云"),

    ;

    private String type;
    private String desc;

    public static CloudSourceType find(String type) {
        for (CloudSourceType item : values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }
}
