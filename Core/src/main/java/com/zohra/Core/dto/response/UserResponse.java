package com.zohra.Core.dto.response;

public record UserResponse(
    Long userId, String username, String email, String phoneNumber, String address) {}
