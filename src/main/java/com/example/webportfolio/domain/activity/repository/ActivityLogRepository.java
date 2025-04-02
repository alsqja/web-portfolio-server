package com.example.webportfolio.domain.activity.repository;

import com.example.webportfolio.domain.activity.entity.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
    Optional<ActivityLog> findByVisitIdAndPortfolioId(String visitId, String portfolioId);

    List<ActivityLog> findByPortfolioId(String portfolioId);
}
