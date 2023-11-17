package com.zohra.OrderService.model;

public record OrderRequest(
        Long productId,
        Long amount,
        Long quantity,
        PaymentMode paymentMode

) {
}
