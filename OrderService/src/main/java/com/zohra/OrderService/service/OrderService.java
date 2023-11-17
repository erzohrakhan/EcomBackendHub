package com.zohra.OrderService.service;

import com.zohra.OrderService.model.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
