package com.witness.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    public void testDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            System.out.println("服务名称:" + s);
        }
    }
}
