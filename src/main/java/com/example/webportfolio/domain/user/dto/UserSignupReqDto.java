package com.example.webportfolio.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSignupReqDto {

    @NotBlank
    private final String name;

    @Email
    private final String email;

    @NotBlank
    private final String password;

    public UserSignupReqDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
