package com.cloud.matrix.biz.service.access.model;

import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.core.model.access.UserRole;
import com.cloud.matrix.util.DateUtil;
import com.cloud.matrix.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author michael
 * @version $Id: UserDetail.java, v 0.1 2023-03-07 10:48 AM Michael Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private Long             id;

    private String           userId;

    private String           name;

    private String           avatar;

    /** 唯一标识码，如type是电话时则为电话号码 */
    private String           identifier;

    /** 唯一标识码类型：邮箱/手机号/微信/支付宝/钉钉等，支持第三方登录 */
    private UserIdentityType identityType;

    private String           token;

    private List<UserRole>   roles;

    private List<String>     tenants;

    public static void patchUser(UserDetail userDetail, User user) {
        if (Objects.nonNull(user)) {
            BeanCopier copier = BeanCopier.create(User.class, UserDetail.class, false);
            copier.copy(user, userDetail, null);
        }
    }

    public static void patchUserAuth(UserDetail userDetail, UserAuth userAuth) {
        if (Objects.nonNull(userAuth)) {
            BeanCopier copier = BeanCopier.create(UserAuth.class, UserDetail.class, false);
            copier.copy(userAuth, userDetail, null);

            // 生成token
            Date date = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
            String timeout = DateUtil.DEFAULT_FORMAT2.format(date);
            String audience = userAuth.getUserId() + "#" + userAuth.getIdentityType() + "#"
                              + userAuth.getIdentifier() + "#" + timeout;
            String token = JwtUtil.encode(audience, userAuth.getCredential());
            userDetail.setToken(token);
        }
    }
}
