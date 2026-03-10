package com.fintech.event_analytics_engine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TransactionRequest {

    @NotBlank(message = "UserId cannot be empty")
    private String userId;

    @Positive(message = "Amount must be positive")
    private Double amount;

    public String getUserId() { return userId; }
    public Double getAmount() { return amount; }
}