package com.fintech.event_analytics_engine.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fintech.event_analytics_engine.event.TransactionCreatedEvent;
//produces the event to the topic, which will be consumed by the consumer and processed to update the analytics data in the database
@Service
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(TransactionCreatedEvent event) {
        kafkaTemplate.send("transaction-topic", event);
    }
}