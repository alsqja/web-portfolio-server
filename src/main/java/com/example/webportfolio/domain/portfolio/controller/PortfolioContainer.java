package com.example.webportfolio.domain.portfolio.controller;

import com.example.webportfolio.domain.portfolio.dto.PortfolioResDto;
import com.example.webportfolio.domain.portfolio.service.PortfolioService;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioContainer {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<PortfolioResDto> createPortfolio(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        return new ResponseEntity<>(portfolioService.create(file, user.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(
            @PathVariable String id,
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        portfolioService.deleteById(id, user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
