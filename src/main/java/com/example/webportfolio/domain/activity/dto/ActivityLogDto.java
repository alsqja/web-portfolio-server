package com.example.webportfolio.domain.activity.dto;

import com.example.webportfolio.domain.activity.entity.ActivityLog;
import com.example.webportfolio.domain.activity.entity.PageLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLogDto {

    private String visitId;
    private String portfolioId;
    private List<PageLog> pageLogs;

    public ActivityLogDto(ActivityLog activityLog) {
        this.visitId = activityLog.getVisitId();
        this.portfolioId = activityLog.getPortfolioId();
        this.pageLogs = activityLog.getPageLogs();
    }

    public ActivityLog toEntity() {
        return new ActivityLog(visitId, portfolioId, pageLogs);
    }
}
