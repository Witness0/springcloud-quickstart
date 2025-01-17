package com.witness.order.service;

import com.witness.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order createOrder(Long userId, Long productId);
}
