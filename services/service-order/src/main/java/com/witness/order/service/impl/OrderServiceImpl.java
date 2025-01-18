package com.witness.order.service.impl;

import com.witness.order.entity.Order;
import com.witness.order.feign.ProductFeignClient;
import com.witness.order.service.OrderService;
import com.witness.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 创建订单
     *
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public Order createOrder(Long userId, Long productId) {
        Order order = new Order();
        order.setId(1L);
        //远程调用获取商品信息
//        Product product = getProductFromRemote(productId);
//        Product product = getProductFromRemoteWithLoadBalancer(productId);
//        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        //使用 openfeign 远程调用查询商品信息
        Product product = productFeignClient.getProductById(productId);
        //总金额
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(product.getNum())));
        order.setUserId(userId);
        order.setAddress("团结屯");
        order.setNickName("熊大");
        //远程调用查询商品列表
        order.setProductList(Arrays.asList(product));

        return order;
    }

    /**
     * 远程调用查询商品信息
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemote(Long productId) {
        //1、获取到商品服务所在的所有机器IP和port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        //2、挑选一个获取其信息，拼接url  http://localhsot:8000/product/1
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;

        //3、发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        log.info("发送远程调用请求：{}", url);
        return product;
    }

    /**
     * 负载均衡远程调用查询商品信息
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        //1、负载均衡获取商品信息
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;

        //2、发送请求
        Product product = restTemplate.getForObject(url, Product.class);
        log.info("发送远程调用请求：{}", url);
        return product;
    }

    /**
     * 注解的方式负载均衡远程调用查询商品信息
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        //使用注解负载均衡时，service-product会被动态替换为对应的机器IP和端口号
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
