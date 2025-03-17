package com.example.webportfolio.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginReqDto {

    @Email
    private final String email;

    @NotBlank
    private final String password;

    public UserLoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
