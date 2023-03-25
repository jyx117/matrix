package com.cloud.matrix.service.enums.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $Id: ProviderConfigEnum.java, v 0.1 2023-03-17 3:03 PM Michael Exp $$
 */
@Getter
@AllArgsConstructor
public enum ProviderConfigEnum {

    SITE("SITE", "阿里云账号所属站点: 中国站、国际站"),

    ;

    private String key;
    private String desc;
}
