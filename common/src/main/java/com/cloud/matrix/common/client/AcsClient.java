package com.cloud.matrix.common.client;

import com.cloud.matrix.common.client.enums.Api;

import java.util.List;
import java.util.Map;

/**
 * @author michael
 * @version $ID: AcsClient.java, v0.1 2023-03-26 14:51 michael Exp
 */
public interface AcsClient {

    List listInstances(Api api, String ak, String sk, List<String> regions, Map param);
}
