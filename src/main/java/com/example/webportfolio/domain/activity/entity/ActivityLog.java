package com.example.webportfolio.domain.activity.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@Document(collation = "activity_logs")
public class ActivityLog {

    @Id
    private String id;

    private String visitId;
    private String portfolioId;
    private List<PageLog> pageLogs;

    public ActivityLog(String visitId, String portfolioId, List<PageLog> pageLogs) {
        this.visitId = visitId;
        this.portfolioId = portfolioId;
        this.pageLogs = pageLogs;
    }
}
