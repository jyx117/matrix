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
public enum AccountType {

    USER("USER", "个人账号: 允许登录控制台、申请权限，不允许共享账号、申请ak"),

    API("API", "api账号: 允许申请ak、申请权限、共享账号，不允许登录控制台"),

    DEPARTMENT("DEPARTMENT", "部门账号: 允许登录控制台、申请权限、共享账号，不允许申请ak"),

    ;

    private String type;
    private String desc;

    public static AccountType find(String type) {
        for (AccountType item : values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }
}
