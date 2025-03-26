package com.example.webportfolio.domain.activity.service;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "activity-log";

    public void sendLog(ActivityLogDto logRequest) {
        kafkaTemplate.send(TOPIC, logRequest);
    }
}
