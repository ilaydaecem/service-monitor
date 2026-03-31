package com.iozturk.service_monitor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableScheduling
public class MonitorService {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final List<String> urls = List.of(
            "https://www.google.com",
            "https://www.github.com",
            "https://www.volthread.com"
    );

    private final Map<String, CheckResult> results = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay = 30000)
    public void checkAll() {
        for (String url : urls) {
            try {
                long start = System.currentTimeMillis();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
                HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());
                long responseTime = System.currentTimeMillis() - start;

                results.put(url, new CheckResult(url, response.statusCode(), responseTime, true, LocalDateTime.now()));
            } catch (Exception e) {
                results.put(url, new CheckResult(url, 0, -1, false, LocalDateTime.now()));
            }
        }
    }

    public List<CheckResult> getResults() {
        return new ArrayList<>(results.values());
    }

    public record CheckResult(String url, int statusCode, long responseTimeMs, boolean isUp, LocalDateTime checkedAt) {}
}