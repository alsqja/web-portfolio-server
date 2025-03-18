package com.example.webportfolio.domain.portfolio.dto;

import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PortfolioResDto {

    private final String id;
    private final String fileName;
    private final String fileUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PortfolioResDto(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.fileName = portfolio.getFileName();
        this.fileUrl = portfolio.getFileUrl();
        this.createdAt = portfolio.getCreatedAt();
        this.updatedAt = portfolio.getUpdatedAt();
    }
}
