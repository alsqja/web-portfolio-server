package com.example.webportfolio.domain.user.dto;

import com.example.webportfolio.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResDto {

    private final String id;
    private final String email;
    private final String name;

    public UserResDto(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UserResDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
