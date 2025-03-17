package com.example.webportfolio.domain.user.controller;

import com.example.webportfolio.domain.user.dto.TokenDto;
import com.example.webportfolio.domain.user.dto.UserLoginReqDto;
import com.example.webportfolio.domain.user.dto.UserResDto;
import com.example.webportfolio.domain.user.dto.UserSignupReqDto;
import com.example.webportfolio.domain.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResDto> signup(@Valid @RequestBody UserSignupReqDto dto) {

        return new ResponseEntity<>(authService.signup(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody UserLoginReqDto dto) {

        return new ResponseEntity<>(authService.login(dto), HttpStatus.CREATED);
    }
}
