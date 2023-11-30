package com.zohra.OrderService.controller;

import com.zohra.Core.dto.request.OrderRequest;
import com.zohra.Core.dto.response.OrderResponse;
import com.zohra.OrderService.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger log = LogManager.getLogger(OrderController.class);
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long orderId) {
        log.info("Get order " + orderId );
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);

    }


    @PostMapping("/place-order")
    ResponseEntity<Long> placeOrder(@RequestBody @Valid OrderRequest orderRequest, BindingResult bindingResult) {
        log.info("Order request " + orderRequest);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(buildErrorMessage(bindingResult));
        }
        Long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }


    private String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        bindingResult
                .getFieldErrors()
                .forEach(
                        error ->
                                errors
                                        .append(error.getField())
                                        .append(": ")
                                        .append(error.getDefaultMessage())
                                        .append("; "));
        return errors.toString();
    }

}
