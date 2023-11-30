package com.zohra.Core.dto.response;

import com.zohra.Core.dto.request.PaymentMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record OrderResponse (
        Long userId,
        Long orderId,
        Long productId,
        Double totalAmount,
        Long quantity,
        LocalDateTime orderDate

){}
//    PaymentMode paymentMode