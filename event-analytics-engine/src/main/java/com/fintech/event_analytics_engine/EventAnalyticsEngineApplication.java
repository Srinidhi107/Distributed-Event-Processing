package com.fintech.event_analytics_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;

@EnableKafka
@EnableRetry
@SpringBootApplication
public class EventAnalyticsEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventAnalyticsEngineApplication.class, args);
    }
}