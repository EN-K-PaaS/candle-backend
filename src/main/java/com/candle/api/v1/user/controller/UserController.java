package com.candle.api.v1.user.controller;

import com.candle.api.v1.user.dto.request.LoginRequest;
import com.candle.api.v1.user.dto.response.LoginResponse;
import com.candle.api.v1.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
