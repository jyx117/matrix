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

    ALIBABA_RAM_NULL(Product.ALIBABA_RAM, "null", "空，用于测试"),

    /**
     * 阿里云api start
     */
    ALIBABA_RAM_LIST_USERS(Product.ALIBABA_RAM, "ListUsers", "调用ListUsers接口列出所有RAM用户"),

    ALIBABA_RAM_LIST_GROUPS(Product.ALIBABA_RAM, "ListGroups", "调用ListGroups接口查询用户组列表"),

    ALIBABA_RAM_LIST_ROLES(Product.ALIBABA_RAM, "ListRoles", "调用ListRoles接口列出角色"),

    ALIBABA_RAM_LIST_POLICIES(Product.ALIBABA_RAM, "ListPolicies", "调用ListPolicies接口列出权限策略"),

    ALIBABA_RAM_LIST_USERS_FOR_GROUP(Product.ALIBABA_RAM, "ListUsersForGroup", "查询指定用户组内的RAM用户列表"),

    ALIBABA_RG_LIST_ROLES(Product.ALIBABA_RG, "ListRoles", "调用ListRoles查看角色列表"),

    ALIBABA_RG_LIST_POLICY_ATTACHMENTS(Product.ALIBABA_RG, "ListPolicyAttachments", "调用ListPolicyAttachments查看授权列表"),
    /** 阿里云api end */

    /**
     * 腾讯云api start
     */
    TENCENT_CAM_LIST_USERS(Product.TENCENT_CAM, "ListUsers", "拉取子用户"),

    /** 腾讯云api end */

    ;

    private Product product;
    private String api;
    private String name;
}
