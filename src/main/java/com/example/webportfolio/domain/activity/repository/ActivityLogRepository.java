package com.example.webportfolio.domain.activity.repository;

import com.example.webportfolio.domain.activity.entity.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
}
