package com.cloud.matrix.common.client.alibaba.strategy.ram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.aliyun.ram20150501.models.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.aliyun.ram20150501.Client;
import com.aliyun.teaopenapi.models.Config;
import com.cloud.matrix.common.client.alibaba.AcsFactory;
import com.cloud.matrix.common.client.alibaba.BaseAcsStrategy;
import com.cloud.matrix.common.client.enums.Api;

/**
 * @author michael
 * @version $ID: ListUsers.java, v0.1 2023-03-26 15:58 michael Exp
 */
@Component("ALIBABA_RAM_LIST_GROUPS")
public class ListGroups extends BaseAcsStrategy implements InitializingBean {

    private static final List<String> REGIONS = new ArrayList<>(Arrays.asList("ram.aliyuncs.com"));

    @Override
    public List<String> listRegions(String ak, String sk) {
        return REGIONS;
    }

    @Override
    public List listRegionData(Object client, Map param) {
        Client c = (Client) client;
        String marker = null;
        int maxItems = 1000;
        ListGroupsRequest request = new ListGroupsRequest();
        request.setMaxItems(maxItems);
        List<ListGroupsResponseBody.ListGroupsResponseBodyGroupsGroup> users = new ArrayList<>();
        while (true) {
            try {
                ListGroupsResponse response = c.listGroups(request);
                ListGroupsResponseBody body = response.getBody();
                if (null != body && null != body.groups && null != body.groups.group) {
                    users.addAll(body.groups.group);
                }
                if (null == body || null == body.isTruncated || !body.isTruncated) {
                    break;
                }
                marker = body.marker;
                request.setMarker(marker);
            } catch (Exception e) {
                break;
            }
        }
        return users;
    }

    @Override
    public Object buildClient(String ak, String sk, String region) throws Exception {
        Config config = new Config().setAccessKeyId(ak).setAccessKeySecret(sk);
        config.endpoint = region;
        return new Client(config);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AcsFactory.register(Api.ALIBABA_RAM_LIST_GROUPS, this);
    }
}
