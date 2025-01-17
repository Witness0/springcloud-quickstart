package com.witness.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    public void testLoadBalancer() {
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalancerClient.choose("service-product").getUri().toString());
        }
    }
}
