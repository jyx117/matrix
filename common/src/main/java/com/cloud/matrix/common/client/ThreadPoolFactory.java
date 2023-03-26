package com.cloud.matrix.common.client;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author michael
 * @version $ID: ThreadPoolFactory.java, v0.1 2023-03-26 14:59 michael Exp
 */
@Component
public class ThreadPoolFactory implements InitializingBean {

    public static Map<PoolName, ThreadPoolExecutor> POOL_MAP = new HashMap<>();

    public static ThreadPoolExecutor getExecutor(PoolName poolName) {
        if (POOL_MAP.containsKey(poolName)) {
            return POOL_MAP.get(poolName);
        } else {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
            POOL_MAP.put(poolName, executor);
            return executor;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initMap();
    }

    private void initMap() {
        for (PoolName poolName : PoolName.values()) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
            POOL_MAP.put(poolName, executor);
        }
    }

    public enum PoolName {
        ALIBABA_LIST_INSTANCES,

        TENCENT_LIST_INSTANCES,

        ;
    }
}
