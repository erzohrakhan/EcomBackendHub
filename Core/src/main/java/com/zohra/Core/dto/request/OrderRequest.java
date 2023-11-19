package com.zohra.Core.dto.request;

import java.util.List;

public record OrderRequest(Double totalAmount, Long userId, List<OrderItem> orderItems) {}
