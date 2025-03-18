package com.example.webportfolio.domain.user.controller;

import com.example.webportfolio.domain.portfolio.dto.PortfolioResDto;
import com.example.webportfolio.domain.portfolio.service.PortfolioService;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final PortfolioService portfolioService;

    @GetMapping("/my/portfolios")
    public ResponseEntity<List<PortfolioResDto>> getUserPortfolios(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        return new ResponseEntity<>(portfolioService.findByUserId(user.getId()), HttpStatus.OK);
    }
}
