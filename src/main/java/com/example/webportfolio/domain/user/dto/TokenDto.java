package com.example.webportfolio.domain.user.dto;

import lombok.Getter;

@Getter
public class TokenDto {

    private final String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
