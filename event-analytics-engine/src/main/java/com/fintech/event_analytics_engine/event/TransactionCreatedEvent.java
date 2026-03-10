package com.fintech.event_analytics_engine.event;

public class TransactionCreatedEvent {

    private Long id;
    private String userId;
    private Double amount;

    public TransactionCreatedEvent() {}

    public TransactionCreatedEvent(Long id, String userId, Double amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public Double getAmount() { return amount; }
}