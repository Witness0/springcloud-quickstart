package com.witness.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.witness.order.entity.Order;
import com.witness.order.properties.OrderProperties;
import com.witness.order.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    /**
     * 秒杀订单
     *
     * @param userId
     * @param productId
     * @return
     */
    @SentinelResource(value = "seckill-order", blockHandler = "seckillBlockHandler")
    @GetMapping("/seckill")
    public Order seckill(@RequestParam(value = "userId", defaultValue = "888") Long userId,
                         @RequestParam(value = "productId", defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, BlockException e) {
        System.out.println("seckillFallback...");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常信息：" + e.getClass());
        return order;
    }

    @GetMapping("writeDb")
    public String writeDb() {
        return "writeDb...";
    }

    @GetMapping("/readDb")
    public String readDb() {
        return "readDb...";
    }

}
