package com.cloud.matrix.common;

import com.cloud.matrix.common.client.AcsClientImpl;
import com.cloud.matrix.common.client.AcsFactory;
import com.cloud.matrix.common.client.enums.Api;
import com.cloud.matrix.common.exception.SystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AcsFactory.class, Date.class })
@PowerMockIgnore({ "javax.*.*", "com.sun.*", "org.xml.*", "org.apache.*" })
public class AcsClientTest {

    @InjectMocks
    private AcsClientImpl acsClient;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Date.class);
        PowerMockito.mockStatic(AcsFactory.class);
    }

    @Test
    public void testException() {
        Mockito.when(AcsFactory.getStrategy(Mockito.any())).thenReturn(null);
        Assert.assertThrows(SystemException.class, () -> {
            acsClient.listInstances(Api.ALIBABA_RAM_NULL, "", "", null, null);
        });
    }
}
