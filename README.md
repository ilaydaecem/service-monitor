# Service Monitor

A lightweight service monitoring tool built with Spring Boot, inspired by tools like [Volthread VOI](https://www.volthread.com/tr/urunler/voi-volthread-webservis-izleme-ve-olay-yonetimi-cozumu/).

## What it does

- Periodically checks the availability of web services every 30 seconds
- Displays HTTP status codes and response times
- Shows UP/DOWN status with a clean dashboard
- Auto-refreshes every 30 seconds

## Tech Stack

- Java 21
- Spring Boot 4.0.5
- Spring Web + Thymeleaf
- Spring Boot Actuator
- Maven

## How to run

### Prerequisites
- Java 21+
- Maven

### Run locally
```bash
./mvnw spring-boot:run
```

Then open your browser and go to:
```
http://localhost:8081
```

## Screenshot

> Dashboard showing real-time status of monitored services.

## About

Built by **Ilayda Öztürk** as a portfolio project.  
Inspired by enterprise service monitoring solutions.