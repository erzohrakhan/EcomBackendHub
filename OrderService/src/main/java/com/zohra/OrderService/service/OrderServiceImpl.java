package com.zohra.OrderService.service;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.OrderService.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        return 0L;
    }
}
