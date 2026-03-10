package com.fintech.event_analytics_engine.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long id) {
        super("Transaction not found with id: " + id);
    }
}