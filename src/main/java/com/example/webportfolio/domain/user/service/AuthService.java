package com.example.webportfolio.domain.user.service;

import com.example.webportfolio.domain.user.dto.UserResDto;
import com.example.webportfolio.domain.user.dto.UserSignupReqDto;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResDto signup(UserSignupReqDto dto) {

        boolean existUser = userRepository.existsByEmail(dto.getEmail());
        if (existUser) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getName());

        return new UserResDto(userRepository.save(user));
    }
}
