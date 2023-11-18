package com.zohra.UserService.controller;

import com.zohra.Core.dto.request.UserRequest;
import com.zohra.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

        private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
        public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest user, BindingResult result) {
            if (result.hasErrors()) {
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }

            UserDTO createdUser = userService.addUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            List<UserDTO> userList = userService.getAllUsers();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }

        @GetMapping("/{userId}")
        public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
            UserDTO user = userService.getUserById(userId);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Additional methods can be added here

    }
}
