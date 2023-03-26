package com.cloud.matrix.common.client.alibaba;

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
    public List listInstances(String ak, String sk, List<String> regions, Map param) {
        return null;
    }
}
