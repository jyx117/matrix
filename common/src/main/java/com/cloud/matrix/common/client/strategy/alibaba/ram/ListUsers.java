package com.cloud.matrix.common.client.strategy.alibaba.ram;

import com.aliyun.ram20150501.Client;
import com.aliyun.ram20150501.models.ListUsersRequest;
import com.aliyun.ram20150501.models.ListUsersResponse;
import com.aliyun.ram20150501.models.ListUsersResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.cloud.matrix.common.client.AcsFactory;
import com.cloud.matrix.common.client.BaseAcsStrategy;
import com.cloud.matrix.common.client.enums.Api;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author michael
 * @version $ID: ListUsers.java, v0.1 2023-03-26 15:58 michael Exp
 */
@Component("ALIBABA_RAM_LIST_USERS")
public class ListUsers extends BaseAcsStrategy implements InitializingBean {

    private static final List<String> REGIONS = new ArrayList<>(Arrays.asList("ram.aliyuncs.com"));

    @Override
    public List<String> listRegions(String ak, String sk) {
        return REGIONS;
    }

    @Override
    public List listRegionData(Object client, Map param) {
        Client c = (Client) client;
        ListUsersRequest request = new ListUsersRequest();
        String marker = null;
        List<ListUsersResponseBody.ListUsersResponseBodyUsersUser> users = new ArrayList<>();
        while (true) {
            try {
                ListUsersResponse response = c.listUsers(request);
                ListUsersResponseBody body = response.getBody();
                if (null != body && null != body.users && null != body.users.user) {
                    users.addAll(body.users.user);
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
        AcsFactory.register(Api.ALIBABA_RAM_LIST_USERS, this);
    }
}
