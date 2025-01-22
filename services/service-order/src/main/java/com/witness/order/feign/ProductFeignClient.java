package com.witness.order.feign;

import com.witness.order.feign.fallback.ProductFeignClientFallback;
import com.witness.product.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// FeignClient注解：
// 标识Feign客户端；
// value：服务名称，会自动去注册中心找到对应的服务；
// fallback：启用兜底回调
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    /*
       mvc注解的两套使用
       1、标注在Controller上，标识接受请求
       2、标注在FeignClient上，标识发送请求
     */
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
