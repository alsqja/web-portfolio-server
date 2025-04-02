package com.example.webportfolio.domain.activity.service;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import com.example.webportfolio.domain.activity.repository.ActivityLogRepository;
import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import com.example.webportfolio.domain.portfolio.repository.PortfolioRepository;
import com.example.webportfolio.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogQueryService {

    private final ActivityLogRepository activityLogRepository;
    private final PortfolioRepository portfolioRepository;

    public List<ActivityLogDto> getLogsByPortfolioId(String portfolioId, User user) {

        Portfolio portfolio = portfolioRepository.findByIdAndUser(portfolioId, user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "portfolio not found"));

        return activityLogRepository.findByPortfolioId(portfolioId).stream().map(ActivityLogDto::new).toList();
    }
}
