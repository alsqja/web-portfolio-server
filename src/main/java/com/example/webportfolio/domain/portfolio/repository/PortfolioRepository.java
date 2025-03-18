package com.example.webportfolio.domain.portfolio.repository;

import com.example.webportfolio.domain.portfolio.entity.Portfolio;
import com.example.webportfolio.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
    List<Portfolio> findByUserId(String id);

    Optional<Portfolio> findByIdAndUser(String id, User user);
}
