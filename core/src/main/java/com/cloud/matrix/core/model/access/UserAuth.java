package com.cloud.matrix.core.model.access;

import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author michael
 * @version $ID: UserDO.java, v0.1 2023-03-01 16:47 michael Exp
 */
@Data
@AllArgsConstructor
public class UserAuth extends BaseModel {

    /** 用户id,和user表user_id字段对应 */
    private String           userId;

    /** 唯一标识码，如type是电话时则为电话号码 */
    private String           identifier;

    /** 唯一标识码类型：邮箱/手机号/微信/支付宝/钉钉等，支持第三方登录 */
    private UserIdentityType identityType;

    /** 密码凭证（站内的保存密码，站外的不保存或保存token）*/
    private String           credential;

}
