package com.zohra.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CloudGatewayController {
    @PostMapping("/order-service-fallback")
    public String orderServiceFallback() {
        return "Order service is down";
    }

    @GetMapping("/user-service-fallback")
    public String userServiceFallback() {
        return "User service is down";
    }

    @GetMapping("/product-service-fallback")
    public String productServiceFallback() {
        return "Product service is down";
    }

}
