package com.iozturk.service_monitor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "check_results")
public class CheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private int statusCode;
    private long responseTimeMs;
    private boolean isUp;
    private LocalDateTime checkedAt;

    public CheckResult() {}

    public CheckResult(String url, int statusCode, long responseTimeMs, boolean isUp, LocalDateTime checkedAt) {
        this.url = url;
        this.statusCode = statusCode;
        this.responseTimeMs = responseTimeMs;
        this.isUp = isUp;
        this.checkedAt = checkedAt;
    }

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public int getStatusCode() { return statusCode; }
    public long getResponseTimeMs() { return responseTimeMs; }
    public boolean isUp() { return isUp; }
    public LocalDateTime getCheckedAt() { return checkedAt; }
}