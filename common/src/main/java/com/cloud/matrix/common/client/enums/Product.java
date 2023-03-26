package com.cloud.matrix.common.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $ID: AlibabaProduct.java, v0.1 2023-03-26 14:55 michael Exp
 */
@Getter
@AllArgsConstructor
public enum Product {

    ALIBABA_RAM(CloudType.ALIBABA, "RAM", "访问控制"),

    ALIBABA_ECS(CloudType.ALIBABA, "ECS", "云服务器ECS"),

    ;

    private CloudType cloudType;

    private String    code;

    private String    name;
}
