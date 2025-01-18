package com.witness.order.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component // 拦截器启用方式：1、注入ioc后，feign会自动启用该拦截器。2、在配置中配置。
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * openfeign请求拦截器
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("请求拦截器XTokenRquestIncepter启动...");
        // 为请求头中添加X-Token
        requestTemplate.header("X-Token", "123456");
    }
}
