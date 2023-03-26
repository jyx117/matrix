package com.cloud.matrix.common.client.alibaba;

import com.cloud.matrix.common.client.ThreadPoolFactory;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.SystemException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author michael
 * @version $ID: BaseAcsStrategy.java, v0.1 2023-03-26 15:15 michael Exp
 */
public abstract class BaseAcsStrategy implements AcsStrategy {

    protected static final ThreadPoolExecutor POOL = ThreadPoolFactory
        .getExecutor(ThreadPoolFactory.PoolName.ALIBABA_LIST_INSTANCES);

    public abstract List<String> listRegions(String ak, String sk);

    public abstract List listRegionData(Object client, Map param);

    public abstract Object buildClient(String ak, String sk, String region) throws Exception;

    @Override
    public List listInstances(String ak, String sk, List<String> regions, Map param) {
        // 1. 获取region
        if (null == regions) {
            regions = listRegions(ak, sk);
        }
        if (null == regions || regions.size() < 1) {
            return null;
        }

        // 2. 异步查询数据
        List<Future> futures = new ArrayList<>();
        for (String region : regions) {
            SyncTask task = new SyncTask(ak, sk, region, param);
            Future future = POOL.submit(task);
            futures.add(future);
        }

        // 3. 获取数据
        List response = new ArrayList();
        for (Future future : futures) {
            try {
                List items = (List) future.get();
                if (null != items) {
                    response.addAll(items);
                }
            } catch (Exception e) {
                continue;
            }
        }

        return response;
    }

    private List listRegionInstances(String ak, String sk, String region, Map param) {
        // 2. 创建config
        Object client = null;
        try {
            client = buildClient(ak, sk, region);
        } catch (Exception e) {
            throw new SystemException(ErrorCode.SYSTEM_BUILD_ACS_CLIENT_EXCEPTION, e);
        }

        // 3. 拉取数据
        return listRegionData(client, param);
    }

    @Data
    @AllArgsConstructor
    protected class SyncTask implements Callable {
        String ak;
        String sk;

        String region;
        Map    param;

        @Override
        public Object call() throws Exception {
            return listRegionInstances(ak, sk, region, param);
        }
    }
}
