package com.zohra.Core.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,
    @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\d{10}", message = "Invalid phone number format, must be 10 digits")
        String phoneNumber,
    @NotBlank(message = "Address is required")
    String address) {}
