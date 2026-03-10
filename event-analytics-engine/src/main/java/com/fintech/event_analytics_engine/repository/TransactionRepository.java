package com.fintech.event_analytics_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.event_analytics_engine.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
