package com.example.webportfolio.domain.portfolio.repository;

import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
    List<Portfolio> findByUserId(String id);
}
