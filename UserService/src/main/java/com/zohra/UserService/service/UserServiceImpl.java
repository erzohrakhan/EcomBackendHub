package com.zohra.UserService.service;

import com.zohra.Core.dto.request.UserRequest;
import com.zohra.Core.dto.response.ProductResponse;
import com.zohra.Core.dto.response.UserResponse;
import com.zohra.UserService.exception.UserNotFoundException;
import com.zohra.UserService.model.User;
import com.zohra.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long addUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setAddress(userRequest.address());
        user.setPhoneNumber(userRequest.phoneNumber());
        user = userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().
                map(user -> {
                    UserResponse userResponse = new UserResponse(
                            user.getUserId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getAddress()
                    );
                    return userResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new UserNotFoundException(
                                                "user with given id not found", "USER_NOT_FOUND"));

        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress());
    }
}
