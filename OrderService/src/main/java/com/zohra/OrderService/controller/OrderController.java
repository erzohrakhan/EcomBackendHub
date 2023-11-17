package com.zohra.OrderService.controller;

import com.zohra.OrderService.model.OrderRequest;
import com.zohra.OrderService.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LogManager.getLogger(OrderController.class);
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
//    @GetMapping(/{"id"})
    @PostMapping("/place-order")
    ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Order request " + orderRequest);
        Long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

}
