package com.candle.api.v1.domain.user.service;

import com.candle.api.v1.domain.user.dto.request.LoginRequest;
import com.candle.api.v1.domain.user.dto.response.LoginResponse;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.repository.UserRepository;
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

        if (userRepository.existsByIdAndPassword(id, password) && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            return new LoginResponse(user.getId(), user.getPhoneNumber(), user.getProfileImage(),
                    user.getName(), user.getIntroduction());
        }
        throw new IllegalArgumentException("Invalid id or password");   // exception 수정 필요
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id에 대한 유저가 존재하지 않습니다."));  // exception 수정 필요
    }

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Transactional
    public String signUp(LoginRequest signUpRequest) {
        if (userRepository.existsById(signUpRequest.id())) {
            throw new IllegalArgumentException("이미 존재하는 id입니다.");  // exception 수정 필요
        }

        User user = new User(signUpRequest.id(), signUpRequest.password(), "010-1234-1234", "admin");
        userRepository.save(user);
        return user.getId();
    }
}
