package com.zohra.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("/reduce-quantity/{id}")
    ResponseEntity<String> reduceQuantity(
            @PathVariable("id") Long productId, @RequestParam Long quantity);
}
