package com.witness.product.service;

import com.witness.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product getProduct(Long productId);
}
