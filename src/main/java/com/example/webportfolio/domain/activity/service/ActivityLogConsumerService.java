package com.example.webportfolio.domain.activity.service;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import com.example.webportfolio.domain.activity.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogConsumerService {

    private final ActivityLogRepository activityLogRepository;

    @KafkaListener(topics = "activity-log", groupId = "activity-log-group")
    public void consume(ActivityLogDto activityLogDto) {
        log.info("[Kafka] 활동 로그 수신: {}", activityLogDto);

        activityLogRepository.save(activityLogDto.toEntity());
    }
}
