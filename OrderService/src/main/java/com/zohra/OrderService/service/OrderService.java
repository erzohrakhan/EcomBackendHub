package com.zohra.OrderService.service;

import com.zohra.Core.dto.request.OrderRequest;
import org.springframework.stereotype.Service;
public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
