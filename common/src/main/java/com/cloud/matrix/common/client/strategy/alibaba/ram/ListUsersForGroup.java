package com.cloud.matrix.common.client.strategy.alibaba.ram;

import com.aliyun.ims20190815.Client;
import com.aliyun.ims20190815.models.ListUsersForGroupRequest;
import com.aliyun.ims20190815.models.ListUsersForGroupResponse;
import com.aliyun.ims20190815.models.ListUsersForGroupResponseBody;
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
@Component("ALIBABA_RAM_LIST_USERS_FOR_GROUP")
public class ListUsersForGroup extends BaseAcsStrategy implements InitializingBean {

    private static final List<String> REGIONS = new ArrayList<>(Arrays.asList("ims.aliyuncs.com"));

    @Override
    public List<String> listRegions(String ak, String sk) {
        return REGIONS;
    }

    @Override
    public List listRegionData(Object client, Map param) {
        Client c = (Client) client;
        ListUsersForGroupRequest request = new ListUsersForGroupRequest();
        String marker = null;
        List<ListUsersForGroupResponseBody.ListUsersForGroupResponseBodyUsersUser> users = new ArrayList<>();
        while (true) {
            try {
                ListUsersForGroupResponse response = c.listUsersForGroup(request);
                ListUsersForGroupResponseBody body = response.getBody();
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
        AcsFactory.register(Api.ALIBABA_RAM_LIST_USERS_FOR_GROUP, this);
    }
}
