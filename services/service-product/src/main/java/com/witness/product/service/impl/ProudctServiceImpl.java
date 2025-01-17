package com.witness.product.service.impl;

import com.witness.product.entity.Product;
import com.witness.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProudctServiceImpl implements ProductService {
    @Override
    public Product getProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setNum(100);
        product.setPrice(new BigDecimal(200));
        product.setProductName("华为手机-"+productId);
        return product;
    }
}
