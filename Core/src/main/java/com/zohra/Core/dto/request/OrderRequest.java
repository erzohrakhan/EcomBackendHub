package com.zohra.Core.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @NotBlank(message = "UserId cannot be blank")
        Long userId,

        @NotBlank(message = "ProductID cannot be blank")
        Long productId,

        @Positive(message = "totalAmount cannot be less than zero")
        Double totalAmount,

        @Positive(message = "Quantity cannot be less than zero")
        Long quantity,

        @NotNull(message = "paymentMode cannot be null")
        PaymentMode paymentMode
) {}
