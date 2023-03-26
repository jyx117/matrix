package com.cloud.matrix.biz.service.access.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author michael
 * @version $ID: RegisterUserRequest.java, v0.1 2023-03-05 08:06 michael Exp
 */
@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 32, message = "用户名最长不能超过32")
    private String name;

    private String avatar;

    /** 用户注册类型 */
    @NotBlank(message = "用户注册类型不能为空")
    private String identityType;

    /** 标识，第三方应用的唯一标识 */
    private String identifier;

    /** 密码 */
    private String credential;
}