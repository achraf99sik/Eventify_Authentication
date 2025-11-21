package com.eventify.authentication.controller;

import com.eventify.authentication.entity.User;
import com.eventify.authentication.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {
        return userService.CreateUser(user);
    }
}
