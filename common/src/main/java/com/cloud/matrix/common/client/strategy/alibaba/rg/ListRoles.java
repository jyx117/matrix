package com.cloud.matrix.common.client.strategy.alibaba.rg;

import com.aliyun.resourcemanager20200331.Client;
import com.aliyun.resourcemanager20200331.models.ListRolesRequest;
import com.aliyun.resourcemanager20200331.models.ListRolesResponse;
import com.aliyun.resourcemanager20200331.models.ListRolesResponseBody;
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
@Component("ALIBABA_RG_LIST_ROLES")
public class ListRoles extends BaseAcsStrategy implements InitializingBean {

    private static final List<String> REGIONS = new ArrayList<>(Arrays.asList("resourcemanager.aliyuncs.com"));

    @Override
    public List<String> listRegions(String ak, String sk) {
        return REGIONS;
    }

    @Override
    public List listRegionData(Object client, Map param) {
        Client c = (Client) client;
        int page = null == param ? 1 : (Integer) param.getOrDefault("page", 1);
        int size = null == param ? 100 : (Integer) param.getOrDefault("size", 10);
        ListRolesRequest request = new ListRolesRequest();
        request.setPageNumber(page);
        request.setPageSize(size);
        List<ListRolesResponseBody.ListRolesResponseBodyRolesRole> items = new ArrayList<>();
        while (true) {
            try {
                ListRolesResponse response = c.listRoles(request);
                ListRolesResponseBody body = response.getBody();
                if (null != body && null != body.roles && null != body.roles.role) {
                    items.addAll(body.roles.role);
                }
                if (null == body || page * size >= body.totalCount) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        return items;
    }

    @Override
    public Object buildClient(String ak, String sk, String region) throws Exception {
        Config config = new Config().setAccessKeyId(ak).setAccessKeySecret(sk);
        config.endpoint = region;
        return new Client(config);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AcsFactory.register(Api.ALIBABA_RG_LIST_ROLES, this);
    }
}
