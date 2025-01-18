package com.witness.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    /**
     * restTemplate远程调用
     *
     * @return
     */
    @LoadBalanced//注解式负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * feign日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * feign重试次数
     *
     * @return
     */
    @Bean
    Retryer feignRetryer() {
        // 参数：重试间隔时间，最大重试时间，最大重试次数
        return new Retryer.Default(100, 500, 3);
    }
}
