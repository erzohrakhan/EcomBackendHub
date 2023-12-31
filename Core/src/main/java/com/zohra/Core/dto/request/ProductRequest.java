package com.zohra.Core.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message = "Product name cannot be blank") String name,
    @Positive(message = "Price must be a positive number") long price,
    @Positive(message = "Quantity must be a positive number") long quantity) {}
