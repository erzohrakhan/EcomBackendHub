package com.zohra.UserService.controller;

import com.zohra.Core.dto.request.UserRequest;
import com.zohra.Core.dto.response.UserResponse;
import com.zohra.UserService.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(buildErrorMessage(bindingResult));
        }

        long userID = userService.addUser(user);
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        bindingResult
                .getFieldErrors()
                .forEach(
                        error ->
                                errors
                                        .append(error.getField())
                                        .append(": ")
                                        .append(error.getDefaultMessage())
                                        .append("; "));
        return errors.toString();
    }

}

