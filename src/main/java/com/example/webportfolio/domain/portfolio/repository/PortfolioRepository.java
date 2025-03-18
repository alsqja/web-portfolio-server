package com.example.webportfolio.domain.portfolio.repository;

import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
}
