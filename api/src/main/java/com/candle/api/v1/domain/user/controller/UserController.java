package com.candle.api.v1.domain.user.controller;

import com.candle.api.v1.domain.user.dto.request.LoginRequest;
import com.candle.api.v1.domain.user.dto.response.LoginResponse;
import com.candle.api.v1.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "로그인", description = "로그인을 수행합니다.")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @Operation(summary = "회원가입", description = "회원가입을 수행합니다.")
    @PostMapping("/sign-up")
    public String signUp(@RequestBody LoginRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }
}
