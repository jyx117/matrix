package com.cloud.matrix.util;

import org.slf4j.Logger;

/**
 * @author michael
 * @version $Id: LogUtil.java, v 0.1 2023-03-03 3:09 PM Michael Exp $$
 */
public class LogUtil {

    /** 摘要日志的内容分隔符 */
    public static final String SEP       = ",";
    public static final String DOT       = ".";

    /** 修饰符 */
    private static final char  RIGHT_TAG = ']';

    /** 修饰符 */
    private static final char  LEFT_TAG  = '[';

    /**
     * 打印info日志。
     *
     * @param logger    日志对象
     * @param objs      任意个要输出到日志的参数
     */
    public static void info(Logger logger, Object... objs) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objs));
        }
    }

    /**
     * 打印info日志。
     *
     * @param logger    日志对象
     * @param ex         异常信息
     * @param objs      任意个要输出到日志的参数
     */
    public static void info(Logger logger, Throwable ex, Object... objs) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(objs), ex);
        }
    }

    /**
     * 打印warn日志。
     *
     * @param logger    日志对象
     * @param objs      任意个要输出到日志的参数
     */
    public static void warn(Logger logger, Object... objs) {
        logger.warn(getLogString(objs));
    }

    /**
     * 打印warn日志。
     *
     * @param logger    日志对象
     * @param ex        异常信息
     * @param objs      任意个要输出到日志的参数
     */
    public static void warn(Logger logger, Throwable ex, Object... objs) {
        logger.warn(getLogString(objs), ex);
    }

    /**
     * 打印error日志。
     *
     * @param logger    日志对象
     * @param ex         异常信息
     * @param objs      任意个要输出到日志的参数
     */
    public static void error(Logger logger, Throwable ex, Object... objs) {
        logger.error(getLogString(objs), ex);
    }

    /**
     * 打印error日志。
     *
     * @param logger    日志对象
     * @param objs      任意个要输出到日志的参数
     */
    public static void error(Logger logger, Object... objs) {
        logger.error(getLogString(objs));
    }

    /**
     * 打印error日志。
     *
     * @param messageName   报警日志的标题
     * @param logger        日志对象
     * @param objs          任意个要输出到日志的参数
     */
    public static void error(String messageName, Logger logger, Object... objs) {
        logger.error(getLogString(messageName, objs));
    }

    /**
     * 打印error日志。
     *
     * @param messageName   报警日志的标题
     * @param logger        日志对象
     * @param objs          任意个要输出到日志的参数
     */
    public static void alert(String messageName, Logger logger, Object... objs) {
        logger.error(getLogString(messageName, objs));
    }

    /**
     * 打印debug日志。
     *
     * @param logger    日志对象
     * @param objs      任意个要输出到日志的参数
     */
    public static void debug(Logger logger, Object... objs) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objs));
        }
    }

    /**
     * 打印debug日志。
     *
     * @param logger    日志对象
     * @param ex        异常信息
     * @param objs      任意个要输出到日志的参数
     */
    public static void debug(Logger logger, Throwable ex, Object... objs) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(objs), ex);
        }
    }

    /**
     * 生成输出到日志的字符串
     * <p>输出格式:[sofaId][messageName]objs...
     *
     * @param messageName   报警日志的标题
     * @param objs          任意个要输出到日志的参数
     * @return              日志字符串
     */
    public static String getLogString(String messageName, Object... objs) {
        StringBuilder log = new StringBuilder();
        log.append(LEFT_TAG);
        // 预留扩展位
        log.append(SEP).append(RIGHT_TAG);

        log.append(LEFT_TAG).append(messageName).append(RIGHT_TAG);

        if (objs != null) {
            for (Object o : objs) {
                log.append(o);
            }
        }

        return log.toString();
    }

    /**
     * 生成输出到日志的字符串
     *
     * @param objs      任意个要输出到日志的参数
     * @return          日志字符串
     */
    public static String getLogString(Object... objs) {
        StringBuilder log = new StringBuilder();
        log.append(LEFT_TAG);
        // 预留扩展位
        log.append(SEP).append(RIGHT_TAG);

        if (objs != null) {
            for (Object o : objs) {
                log.append(o);
            }
        }
        return log.toString();
    }
}
