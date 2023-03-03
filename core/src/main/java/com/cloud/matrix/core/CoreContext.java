package com.cloud.matrix.core;

import com.cloud.matrix.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author michael
 * @version $Id: CoreContext.java, v 0.1 2023-03-02 7:09 PM Michael Exp $$
 */
public class CoreContext {

    /** 租户上下文 */
    private static final ThreadLocal<String>              tenantLocal   = new ThreadLocal<String>();

    /** 配置项 */
    private static Map<String, String>                    config;

    /** 用户上下文 */
    private static final ThreadLocal<String>              userLocal     = new ThreadLocal<String>();

    /** traceId上下文 */
    private static final ThreadLocal<String>              traceLocal    = new ThreadLocal<String>();

    /**
     * 清理租户上下文
     */
    public static void cleanTenantContext() {
        tenantLocal.remove();
    }

    /**
     * 清理user上下文
     */
    public static void cleanUserContext() {
        userLocal.remove();
    }

    /**
     * 清理trace上下文
     */
    public static void cleanTraceContext() {
        traceLocal.remove();
    }

    /**
     * 清理上下文信息
     */
    public static void clean() {
        tenantLocal.remove();
        userLocal.remove();
        traceLocal.remove();
    }

    public static void setTenant(String tenant) {
        tenantLocal.set(tenant);
    }

    public static String getTenant() {
        return tenantLocal.get();
    }

    public static void setUser(String userId) {
        userLocal.set(userId);
    }

    public static String getUser() {
        return userLocal.get();
    }

    public static void setTrace(String userId) {
        traceLocal.set(userId);
    }

    public static String getTrace() {
        return traceLocal.get();
    }

    /**
     * Gets the value of config.
     *
     * @return the value of config
     */
    public static Map<String, String> getConfig() {
        if (config != null) {
            return config;
        }
        // String profile = System.getProperty("spring.profiles.active");
        Properties props = PropertiesUtil.loadProperties("application.properties");
        config = new HashMap<String, String>((Map) props);
        String profile = config.get("spring.profiles.active");
        props = PropertiesUtil.loadProperties("application-" + profile + ".properties");
        config.putAll((Map) props);
        return config;
    }

    /**
     * Gets the value of config.
     *
     * @return the value of config
     */
    public static Map<String, String> getConfigFromActive(String active) {
        if (config != null) {
            return config;
        }
        String profile = System.getProperty("spring.profiles.active");
        Properties props = PropertiesUtil.loadProperties("application.properties");
        config = new HashMap<String, String>((Map) props);
        props = PropertiesUtil.loadProperties("application-" + profile + ".properties");
        config.putAll((Map) props);
        return config;
    }

    /**
     * Sets the config.
     *
     * <p>You can use getConfig() to get the value of config</p>
     *
     * @param config
     *     config
     */
    public static void setConfig(Map<String, String> config) {
        CoreContext.config = config;
    }
}
