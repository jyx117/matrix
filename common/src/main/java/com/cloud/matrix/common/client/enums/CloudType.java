package com.cloud.matrix.common.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $ID: CloudType.java, v0.1 2023-03-26 15:07 michael Exp
 */
@AllArgsConstructor
@Getter
public enum CloudType {

    ALIBABA("ALIBABA", "阿里云"),

    TENANT("TENANT", "腾讯云"),

    HUAWEI("HUAWEI", "华为云"),

    ;

    private String code;

    private String name;
}
