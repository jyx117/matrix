package com.cloud.matrix.common.client.alibaba;

import java.util.List;
import java.util.Map;

/**
 * @author michael
 * @version $ID: AcsStrategy.java, v0.1 2023-03-26 15:13 michael Exp
 */
public interface AcsStrategy {

    List listInstances(String ak, String sk, List<String> regions, Map param);
}
