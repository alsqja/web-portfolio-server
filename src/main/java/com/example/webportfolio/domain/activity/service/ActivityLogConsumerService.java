package com.example.webportfolio.domain.activity.service;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import com.example.webportfolio.domain.activity.entity.ActivityLog;
import com.example.webportfolio.domain.activity.entity.PageLog;
import com.example.webportfolio.domain.activity.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogConsumerService {

    private final ActivityLogRepository activityLogRepository;

    @KafkaListener(topics = "activity-log", groupId = "activity-log-group")
    public void consume(ActivityLogDto activityLogDto) {
        log.info("[Kafka] 활동 로그 수신: {}", activityLogDto);

        String visitId = activityLogDto.getVisitId();
        String portfolioId = activityLogDto.getPortfolioId();
        List<PageLog> newLogs = activityLogDto.getPageLogs();

        Optional<ActivityLog> existingLogOpt = activityLogRepository.findByVisitIdAndPortfolioId(visitId, portfolioId);

        if (existingLogOpt.isPresent()) {
            ActivityLog existingLog = existingLogOpt.get();

            Map<Integer, Long> mergedMap = new HashMap<>();
            for (PageLog pageLog : existingLog.getPageLogs()) {
                mergedMap.put(pageLog.getPage(), pageLog.getDurationMs());
            }

            for (PageLog newLog : newLogs) {
                mergedMap.merge(
                        newLog.getPage(),
                        newLog.getDurationMs(),
                        Long::sum
                );
            }

            List<PageLog> mergedLogs = mergedMap.entrySet().stream()
                    .map(entry -> new PageLog(entry.getKey(), entry.getValue()))
                    .sorted(Comparator.comparingInt(PageLog::getPage))
                    .toList();

            existingLog.updatePageLogs(mergedLogs);
            activityLogRepository.save(existingLog);
            log.info("[Kafka] 기존 로그 병합 저장 완료: visitId={}, portfolioId={}", visitId, portfolioId);
        } else {
            activityLogRepository.save(activityLogDto.toEntity());
            log.info("[Kafka] 새 로그 저장 완료: visitId={}, portfolioId={}", visitId, portfolioId);
        }
    }
}
