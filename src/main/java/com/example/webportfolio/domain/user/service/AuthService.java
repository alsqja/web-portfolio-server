package com.example.webportfolio.domain.user.service;

import com.example.webportfolio.domain.user.dto.TokenDto;
import com.example.webportfolio.domain.user.dto.UserLoginReqDto;
import com.example.webportfolio.domain.user.dto.UserResDto;
import com.example.webportfolio.domain.user.dto.UserSignupReqDto;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.domain.user.repository.UserRepository;
import com.example.webportfolio.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public UserResDto signup(UserSignupReqDto dto) {

        boolean existUser = userRepository.existsByEmail(dto.getEmail());
        if (existUser) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        User user = new User(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getName());

        return new UserResDto(userRepository.save(user));
    }

    public TokenDto login(UserLoginReqDto dto) {

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtProvider.generateToken(user);

        return new TokenDto(accessToken);
    }
}
