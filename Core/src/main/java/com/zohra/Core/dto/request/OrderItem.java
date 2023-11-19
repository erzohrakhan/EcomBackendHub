package com.zohra.Core.dto.request;

public record OrderItem(Long order_id, Long product_id, Long quantity, Double unitPrice) {}
