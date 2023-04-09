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

    /** 阿里云产品start */
    ALIBABA_RAM(CloudType.ALIBABA, "RAM", "访问控制"),

    ALIBABA_RG(CloudType.ALIBABA, "RG", "资源管理"),

    ALIBABA_ECS(CloudType.ALIBABA, "ECS", "云服务器ECS"),
    /** 阿里云产品end */

    /** 腾讯云产品start */
    TENCENT_CAM(CloudType.TENANT, "CAM", "访问管理"),

    /** 腾讯云产品end */

    ;

    private CloudType  cloudType;

    private String     code;

    private String     name;
}
