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

    ALIBABA_RAM_LIST_USERS(Product.ALIBABA_RAM, "ListUsers", "调用ListUsers接口列出所有RAM用户"),

    ALIBABA_RAM_LIST_GROUPS(Product.ALIBABA_RAM, "ListGroups", "调用ListGroups接口查询用户组列表"),

    ;

    private Product product;
    private String  api;
    private String  name;
}
