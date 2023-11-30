package com.zohra.OrderService.service;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.Core.dto.response.OrderResponse;
import org.springframework.stereotype.Service;
public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(Long orderId);
}
