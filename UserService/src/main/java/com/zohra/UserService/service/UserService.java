package com.zohra.UserService.service;

import com.zohra.Core.dto.request.UserRequest;
import com.zohra.Core.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    long addUser(UserRequest user);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);
}
