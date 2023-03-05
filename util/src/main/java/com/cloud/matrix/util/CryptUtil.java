package com.cloud.matrix.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具
 * @author michael
 * @version $ID: CryptUtil.java, v0.1 2023-03-05 07:58 michael Exp
 */
public class CryptUtil {

    public static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    /**
     * @param password   用户输入的明文密码
     * @param encode     数据库中的加密密码
     * @return
     */
    public static final Boolean matches(String password, String encode) {
        return PASSWORD_ENCODER.matches(password, encode);
    }
}
