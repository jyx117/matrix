package com.cloud.matrix.common.client;

import com.cloud.matrix.common.client.enums.Api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author michael
 * @version $ID: AcsFactory.java, v0.1 2023-03-26 15:54 michael Exp
 */
public class AcsFactory {

    private static Map<Api, AcsStrategy> strategyMap = new HashMap<>();

    public static AcsStrategy getStrategy(Api api) {
        return strategyMap.get(api);
    }

    public static void register(Api api, AcsStrategy strategy) {
        strategyMap.put(api, strategy);
    }

    public AcsFactory() {
    }
}
