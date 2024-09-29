package com.candle.api.v1.user.service;

import com.candle.api.v1.user.dto.request.LoginRequest;
import com.candle.api.v1.user.dto.response.LoginResponse;
import com.candle.api.v1.user.entity.UserEntity;
import com.candle.api.v1.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        String id = loginRequest.id();
        String password = loginRequest.password();

        if (isExistUser(id, password)) {
            UserEntity user = userRepository.findById(id).get();
            return new LoginResponse(user.getId(), user.getPhoneNumber(), user.getProfileImage(),
                    user.getName(), user.getIntroduction());
        }
        throw new IllegalArgumentException("Invalid id or password");   // exception 수정 필요
    }

    private boolean isExistUser(String id, String password) {
        return userRepository.existsByIdAndPassword(id, password) && userRepository.findById(id).isPresent();
    }
}
