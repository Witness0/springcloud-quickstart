package com.witness.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "order") //批量配置绑定在nacos下，可以无需@RefreshScope注解就可以实现自动刷新
@Component
public class OrderProperties {
    //默认开启驼峰映射

    private String timeout;

    private String autoConfirm;

    private String dbUrl;
}
