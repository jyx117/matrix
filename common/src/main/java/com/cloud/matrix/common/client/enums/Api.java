package com.cloud.matrix.common.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author michael
 * @version $ID: AlibabaApi.java, v0.1 2023-03-26 14:56 michael Exp
 */
@AllArgsConstructor
@Getter
public enum Api {

    /** 阿里云api start */
    ALIBABA_RAM_LIST_USERS(Product.ALIBABA_RAM, "ListUsers", "调用ListUsers接口列出所有RAM用户"),

    ALIBABA_RAM_LIST_GROUPS(Product.ALIBABA_RAM, "ListGroups", "调用ListGroups接口查询用户组列表"),
    /** 阿里云api end */

    /** 腾讯云api start */
    TENCENT_CAM_LIST_USERS(Product.TENCENT_CAM, "ListUsers", "拉取子用户"),

    /** 腾讯云api end */

    ;

    private Product  product;
    private String   api;
    private String   name;
}
