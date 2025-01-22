package com.witness.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局filter
 */
@Component
@Slf4j
public class RtGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("请求【{}】开始：时间：{}", exchange.getRequest().getURI(), System.currentTimeMillis());
        //********以上是前置逻辑**********

        Mono<Void> filter = chain.filter(exchange)
                .doFinally((result) -> {
                    //********以下是后置逻辑**********
                    long end = System.currentTimeMillis();
                    log.info("请求【{}】结束：时间：{}", exchange.getRequest().getURI(), end);
                });//放行 10s


        return filter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
