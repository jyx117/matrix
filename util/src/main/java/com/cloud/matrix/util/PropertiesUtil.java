package com.cloud.matrix.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author michael
 * @version $Id: PropertiesUtil.java, v 0.1 2023-03-02 7:10 PM Michael Exp $$
 */
public class PropertiesUtil {

    /**
     * Load property file resources
     *
     * @param fileName
     * @return
     */
    public static Properties loadProperties(String fileName) {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        return loadProperties(in);
    }

    /**
     * Load property file resources
     *
     * @param in
     * @return
     */
    public static Properties loadProperties(InputStream in) {
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (Exception e) {
        }
        return properties;
    }
}
