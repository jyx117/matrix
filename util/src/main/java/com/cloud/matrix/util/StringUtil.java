package com.cloud.matrix.util;

/**
 * @author michael
 * @version $Id: StringUtil.java, v 0.1 2023-03-06 11:38 AM Michael Exp $$
 */
public class StringUtil {

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equals(str2);
        }
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        } else {
            return str1.equalsIgnoreCase(str2);
        }
    }
}
