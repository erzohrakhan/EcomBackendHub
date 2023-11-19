package com.zohra.OrderService.controller;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.OrderService.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LogManager.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

//    @Autowired
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @PostMapping("/place-order")
    ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Order request " + orderRequest);
        Long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

}
