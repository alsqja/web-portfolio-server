package com.example.webportfolio.domain.activity.controller;

import com.example.webportfolio.domain.activity.dto.ActivityLogDto;
import com.example.webportfolio.domain.activity.service.ActivityLogProducerService;
import com.example.webportfolio.domain.activity.service.ActivityLogQueryService;
import com.example.webportfolio.domain.user.entity.User;
import com.example.webportfolio.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogProducerService activityLogProducerService;
    private final ActivityLogQueryService activityLogQueryService;

    @PostMapping
    public ResponseEntity<Void> sendActivityLog(@RequestBody ActivityLogDto activityLogDto) {

        activityLogProducerService.sendLog(activityLogDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/portfolios/{id}")
    public ResponseEntity<List<ActivityLogDto>> getActivityLog(
            @PathVariable String id,
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        return new ResponseEntity<>(activityLogQueryService.getLogsByPortfolioId(id, user), HttpStatus.OK);
    }
}
