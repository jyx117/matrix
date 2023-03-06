package com.cloud.matrix.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author michael
 * @version $ID: IdGenerateUtil.java, v0.1 2023-03-05 08:39 michael Exp
 */
public class IdGenerateUtil {

    /** 生成8位uuid的字母 */
    private static final String[]   chars                = new String[] { "a", "b", "c", "d", "e",
                                                                          "f", "g", "h", "i", "j",
                                                                          "k", "l", "m", "n", "o",
                                                                          "p", "q", "r", "s", "t",
                                                                          "u", "v", "w", "x", "y",
                                                                          "z", "0", "1", "2", "3",
                                                                          "4", "5", "6", "7", "8",
                                                                          "9", "A", "B", "C", "D",
                                                                          "E", "F", "G", "H", "I",
                                                                          "J", "K", "L", "M", "N",
                                                                          "O", "P", "Q", "R", "S",
                                                                          "T", "U", "V", "W", "X",
                                                                          "Y", "Z" };

    private static final String     USER_PASSWORD_PREFIX = "USER";
    private static final String     USER_PHONE_PREFIX    = "USER-PHONE";
    private static final String     USER_DD_PREFIX       = "USER-DD";

    private static final String     USER_WECHAT_PREFIX       = "USER-WECHAT";

    /** 时间df */
    private static SimpleDateFormat DF                   = new SimpleDateFormat("yyyyMMddHHmmssS");

    /**
     * 生产user表id
     * @return
     */
    public static String generateUserId() {
        return generateId(USER_PASSWORD_PREFIX);
    }

    /**
     * 生产user表微信id
     * @return
     */
    public static String generateUserWechatId() {
        return generateId(USER_WECHAT_PREFIX);
    }

    /**
     * 生产user表用电话注册的id
     * @return
     */
    public static String generateUserPhoneId() {
        return generateId(USER_PHONE_PREFIX);
    }

    /**
     * 生产user表用钉钉注册的id
     * @return
     */
    public static String generateUserDdId() {
        return generateId(USER_DD_PREFIX);
    }

    /**
     * 根据前缀创建id
     * @param prefix
     *
     * @return
     */
    public static String generateId(String prefix) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append("_");
        builder.append(getUUID8());
        builder.append("_");
        builder.append(getTime8());
        return builder.toString();
    }

    /**
     * 获取8位UUID
     * @return
     */
    public static String getUUID8() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取8位时间字符串
     * @return
     */
    public static synchronized String getTime8() {
        return DF.format(new Date());
    }
}
