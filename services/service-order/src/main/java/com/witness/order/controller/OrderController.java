package com.witness.order.controller;

import com.witness.order.entity.Order;
import com.witness.order.properties.OrderProperties;
import com.witness.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope //激活配置属性的自动刷新
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    //    @Value("${order.timeout}")
//    private String orderTimeout;
//    @Value("${order.auto-confirm}")
//    private String orderAutoConfirm;
    @Autowired
    private OrderProperties orderProperties;

    /**
     * 测试配置中心
     *
     * @return
     */
    @GetMapping("/config")
    public String config() {
        return "order.timeout:" +
                orderProperties.getTimeout() +
                ", order.autoConfirm:" +
                orderProperties.getAutoConfirm() +
                ", order.dbUrl:" +
                orderProperties.getDbUrl();
    }

    /**
     * 创建订单
     *
     * @param userId
     * @param productId
     * @return
     */
    @GetMapping("/create")
    public Order createOrder(@PathParam("userId") Long userId,
                             @PathParam("productId") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        return order;
    }


}
