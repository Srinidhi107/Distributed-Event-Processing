package com.fintech.fraud_detection.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fintech.fraud_detection.event.TransactionCreatedEvent;

@Service
public class FraudConsumer {

    private static final Logger logger =
            LoggerFactory.getLogger(FraudConsumer.class);

    @KafkaListener(topics = "transaction-topic", groupId = "fraud-group")
    public void consume(TransactionCreatedEvent event) {

        logger.info("Fraud Service received transaction {}", event.getId());

        if(event.getAmount() > 10000) {
            logger.warn("🚨 FRAUD ALERT for transaction {}", event.getId());
        }
    }
}