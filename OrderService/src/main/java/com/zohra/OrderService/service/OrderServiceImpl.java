package com.zohra.OrderService.service;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.OrderService.entity.Order;
import com.zohra.OrderService.external.client.ProductService;
import com.zohra.OrderService.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class OrderServiceImpl implements OrderService{
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        log.info("Placing order request{}", orderRequest);
        productService.reduceQuantity(orderRequest.productId(), orderRequest.quantity());

        Order order = new Order();
        order.setAmount(orderRequest.totalAmount());
        order.setProductId(orderRequest.productId());
        order.setQuantity(orderRequest.quantity());
        order.setOrderStatus("CREATED");
        orderRepository.save(order);

        return order.getOrderId();
    }
}
