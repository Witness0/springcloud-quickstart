package com.witness.order.feign.fallback;

import com.witness.order.feign.ProductFeignClient;
import com.witness.product.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//openfeign的兜底机制要配合sentinel来实现
@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    /**
     * 根据商品id查询商品信息(兜底回调，即原远程调用如果失败，则使用该方法返回)
     * @param id
     * @return
     */
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调...");
        Product product = new Product();
        product.setId(id);
        product.setNum(0);
        product.setPrice(new BigDecimal(0));
        product.setProductName("商品不存在");
        return product;
    }
}
