package com.iozturk.service_monitor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class MonitorService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final CheckResultRepository repository;

    private final List<String> urls = List.of(
            "https://www.google.com",
            "https://www.github.com",
            "https://www.volthread.com"
    );

    public MonitorService(CheckResultRepository repository) {
        this.repository = repository;
    }

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

                repository.save(new CheckResult(url, response.statusCode(), responseTime, true, LocalDateTime.now()));
            } catch (Exception e) {
                repository.save(new CheckResult(url, 0, -1, false, LocalDateTime.now()));
            }
        }
    }

    public List<CheckResult> getResults() {
        return repository.findLatestForEachUrl();
    }
}