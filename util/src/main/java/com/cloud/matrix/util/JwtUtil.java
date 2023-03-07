package com.cloud.matrix.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author michael
 * @version $Id: JwtUtil.java, v 0.1 2023-03-07 2:26 PM Michael Exp $$
 */
public class JwtUtil {

    public static String encode(String audience, String signCode) {
        return JWT.create().withAudience(audience).sign(Algorithm.HMAC256(signCode));
    }

    public static String decode(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    public static void main(String[] args) {
        String audience0 = "1#2#3";
        String audience1 = "1#2#3#2023/03/0711:31:38";
        String audience2 = "1#2#3#2023/03/0717:31:38";
        String audience3 = "uid1#PASSWORD##2023/03/0717:31:38";
        String signCode = "4";
        System.out.println(JwtUtil.encode(audience0, signCode));
        System.out.println(JwtUtil.encode(audience1, signCode));
        System.out.println(JwtUtil.encode(audience2, signCode));
        System.out.println(JwtUtil.encode(audience3, "111"));
    }
}
