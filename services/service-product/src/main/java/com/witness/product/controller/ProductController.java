package com.witness.product.controller;

import com.witness.product.entity.Product;
import com.witness.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/api/product")
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据商品id查询商品信息
     * @param productId
     * @return
     */
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
//        log.info("测试负载均衡：{}", "hello");
        System.out.println("测试负载均衡：hello");
        Product product=productService.getProduct(productId);
        return product;
    }
}
