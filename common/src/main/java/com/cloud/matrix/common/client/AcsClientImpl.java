package com.cloud.matrix.common.client;

import com.cloud.matrix.common.client.enums.Api;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author michael
 * @version $ID: AcsClientImpl.java, v0.1 2023-03-26 15:11 michael Exp
 */
@Service
public class AcsClientImpl implements AcsClient {
    @Override
    public List listInstances(Api api, String ak, String sk, List<String> regions, Map param) {
        AcsStrategy strategy = AcsFactory.getStrategy(api);
        if (null == strategy) {
            throw new SystemException(ErrorCode.SYSTEM_NO_ACS_STRATEGY, api.getApi());
        }
        return strategy.listInstances(ak, sk, regions, param);
    }
}
