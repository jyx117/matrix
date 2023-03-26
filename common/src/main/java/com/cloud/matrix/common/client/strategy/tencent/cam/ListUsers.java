package com.cloud.matrix.common.client.strategy.tencent.cam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.tencentcloudapi.cam.v20190116.CamClient;
import com.tencentcloudapi.cam.v20190116.models.ListUsersRequest;
import com.tencentcloudapi.cam.v20190116.models.ListUsersResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.cloud.matrix.common.client.AcsFactory;
import com.cloud.matrix.common.client.BaseAcsStrategy;
import com.cloud.matrix.common.client.enums.Api;

/**
 * @author michael
 * @version $ID: ListUsers.java, v0.1 2023-03-26 15:58 michael Exp
 */
@Component("TENCENT_CAM_LIST_USERS")
public class ListUsers extends BaseAcsStrategy implements InitializingBean {

    private static final Logger logger  = LoggerFactory.getLogger(ListUsers.class);

    private static final List<String> REGIONS = new ArrayList<>(
        Arrays.asList("cam.tencentcloudapi.com"));

    @Override
    public List<String> listRegions(String ak, String sk) {
        return REGIONS;
    }

    @Override
    public List listRegionData(Object client, Map param) {
        CamClient c = (CamClient) client;
        ListUsersRequest request = new ListUsersRequest();
        try {
            ListUsersResponse response = c.ListUsers(request);
            return null == response || null == response.getData() ? null
                : Arrays.asList(response.getData());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object buildClient(String ak, String sk, String region) throws Exception {
        Credential cred = new Credential(ak, sk);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(region);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new CamClient(cred, "", clientProfile);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AcsFactory.register(Api.TENCENT_CAM_LIST_USERS, this);
    }
}
