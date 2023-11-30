package com.zohra.OrderService.service;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.Core.dto.response.OrderResponse;
import com.zohra.OrderService.entity.Order;
import com.zohra.OrderService.exception.OrderCustomExcpetion;
import com.zohra.OrderService.external.client.ProductService;
import com.zohra.OrderService.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Order order = new Order();
        order.setUserId(orderRequest.userId());
        order.setAmount(orderRequest.totalAmount());
        order.setProductId(orderRequest.productId());
        order.setQuantity(orderRequest.quantity());
        order.setOrderStatus("CREATED");
        orderRepository.save(order);
        productService.reduceQuantity(orderRequest.productId(), orderRequest.quantity());

        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(
                        () -> new OrderCustomExcpetion
                                ("Order number not available",
                                        "INVALID_ORDER_NUMBER"));
        OrderResponse orderResponse = new OrderResponse(
                order.getUserId(),
                order.getOrderId(),
                order.getProductId(),
                order.getAmount(),
                order.getQuantity(),
                order.getOrderDate()
        );

        return orderResponse;
    }
}
