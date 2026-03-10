package com.fintech.event_analytics_engine.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fintech.event_analytics_engine.event.TransactionCreatedEvent;
import com.fintech.event_analytics_engine.exception.TransactionNotFoundException;
import com.fintech.event_analytics_engine.kafka.TransactionProducer;
import com.fintech.event_analytics_engine.model.Transaction;
import com.fintech.event_analytics_engine.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionProducer producer;
    
    private static final Logger logger =
            LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository,
                          TransactionProducer producer) {
    this.repository = repository;
    this.producer = producer;
}

    public Transaction createTransaction(String userId, Double amount) {

    Transaction txn = new Transaction(userId, amount, "SUCCESS");
    Transaction saved = repository.save(txn);

    TransactionCreatedEvent event =
            new TransactionCreatedEvent(
                    saved.getId(),
                    saved.getUserId(),
                    saved.getAmount()
            );

    producer.publishEvent(event);

    return saved;
}

    public List<Transaction> getAllTransactions() {
        logger.info("Fetching all transactions");
        return repository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        logger.info("Fetching transaction with id {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public Transaction updateTransaction(Long id, Double amount) {
        logger.info("Updating transaction with id {}", id);

        Transaction txn = repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        txn.setAmount(amount);
        return repository.save(txn);
    }

    public void deleteTransaction(Long id) {
        logger.info("Deleting transaction with id {}", id);

        if (!repository.existsById(id)) {
            throw new TransactionNotFoundException(id);
        }

        repository.deleteById(id);
    }
}