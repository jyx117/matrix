package com.cloud.matrix.dal.model.access;

import com.cloud.matrix.dal.model.BaseDO;

import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
public class UserAuthDO extends BaseDO {

    // 用户id,和user表user_id字段对应
    private String userId;

    // 唯一标识码，如type是电话时则为电话号码
    private String identifier;

    // 唯一标识码类型：邮箱/手机号/微信/支付宝/钉钉等，支持第三方登录
    private String identityType;

    // 密码凭证（站内的保存密码，站外的不保存或保存token）
    private String credential;

}
