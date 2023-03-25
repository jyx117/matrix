package com.cloud.matrix.service.enums.account;

import com.cloud.matrix.service.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: ProviderSite.java, v 0.1 2023-03-17 3:44 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum ProviderSite {

    CHINA("CHINA", "中国站"),

    OVERSEA("OVERSEA", "国际站"),

    ;

    private String site;
    private String desc;

    public static ProviderSite find(String site) {
        for (ProviderSite item : values()) {
            if (StringUtil.equalsIgnoreCase(site, item.name())) {
                return item;
            }
        }
        return null;
    }
}
