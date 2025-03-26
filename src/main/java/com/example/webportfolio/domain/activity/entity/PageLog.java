package com.example.webportfolio.domain.activity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageLog {
    private Integer page;
    private Long durationMs;
}
