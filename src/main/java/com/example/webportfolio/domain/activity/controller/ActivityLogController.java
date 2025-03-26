package com.example.webportfolio.domain.activity.controller;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import com.example.webportfolio.domain.activity.service.ActivityLogProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogProducerService activityLogProducerService;

    @PostMapping
    public ResponseEntity<Void> sendActivityLog(@RequestBody ActivityLogDto activityLogDto) {

        activityLogProducerService.sendLog(activityLogDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
