package com.zohra.OrderService.external.client;

import com.zohra.OrderService.exception.OrderCustomExcpetion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="PRODUCT-SERVICE/product")
@CircuitBreaker(name = "PRODUCT-SERVICE", fallbackMethod = "fallback")
public interface ProductService {
    @PutMapping("/reduce-quantity/{id}")
    ResponseEntity<String> reduceQuantity(
            @PathVariable("id") Long productId, @RequestParam Long quantity);
    default ResponseEntity<String> fallback(
            @PathVariable("id") Long productId, @RequestParam Long quantity, Throwable throwable) {

        throw new OrderCustomExcpetion("Product service unavailable",
                "PRODUCT_SERVICE_UNAVAILABLE");
    }
}
