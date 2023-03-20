package com.cloud.matrix.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author michael
 * @version $Id: ParamUtil.java, v 0.1 2023-03-20 2:49 PM Michael Exp $$
 */
public class ParamUtil {

    /**
     * 反射创建请求参数
     * @param object
     * @return
     */
    public static Map buildParam(Object object) {
        Map param = new HashMap();
        Field[] fields = object.getClass().getDeclaredFields();
        Integer pageNum = null, pageSize = null;
        if (fields.length > 0) {
            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    Object fieldObj = field.get(object);
                    if (StringUtil.equalsIgnoreCase("pageNum", fieldName)) {
                        pageNum = (Integer) fieldObj;
                        continue;
                    }
                    if (StringUtil.equalsIgnoreCase("pageSize", fieldName)) {
                        pageSize = (Integer) fieldObj;
                        continue;
                    }
                    if (fieldObj instanceof String) {
                        param.put(fieldName, (String) fieldObj);
                    }
                    if (fieldObj instanceof List) {
                        param.put(fieldName, (List) fieldObj);
                    }
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
        }

        if (null == pageNum || null == pageSize) {
            param.put("limitStart", 0);
            param.put("limitEnd", 10);
        } else {
            param.put("limitStart", (pageNum - 1) * pageSize);
            param.put("limitEnd", pageSize);
        }
        return param;
    }
}
