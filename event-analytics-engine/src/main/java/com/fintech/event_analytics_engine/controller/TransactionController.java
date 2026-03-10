package com.fintech.event_analytics_engine.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fintech.event_analytics_engine.dto.ApiResponse;
import com.fintech.event_analytics_engine.dto.TransactionRequest;
import com.fintech.event_analytics_engine.model.Transaction;
import com.fintech.event_analytics_engine.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
public ResponseEntity<ApiResponse<Transaction>> create(
        @Valid @RequestBody TransactionRequest request) {

    Transaction txn = service.createTransaction(
            request.getUserId(),
            request.getAmount()
    );

    return new ResponseEntity<>(
            new ApiResponse<>(201, "Transaction created successfully", txn),
            HttpStatus.CREATED
    );
}

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Transaction>>> getAllTransactions() {
        List<Transaction> transactions = service.getAllTransactions();

        return new ResponseEntity<>(
                new ApiResponse<>(200, "All transactions fetched successfully", transactions),
                HttpStatus.OK
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> getTransactionById(@PathVariable Long id) {
        Transaction txn = service.getTransactionById(id);

        return new ResponseEntity<>(
                new ApiResponse<>(200, "Transaction fetched successfully", txn),
                HttpStatus.OK
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> updateTransaction(
            @PathVariable Long id,
            @RequestParam Double amount) {

        Transaction updatedTxn = service.updateTransaction(id, amount);

        return new ResponseEntity<>(
                new ApiResponse<>(200, "Transaction updated successfully", updatedTxn),
                HttpStatus.OK
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTransaction(@PathVariable Long id) {

        service.deleteTransaction(id);

        return new ResponseEntity<>(
                new ApiResponse<>(204, "Transaction deleted successfully", null),
                HttpStatus.NO_CONTENT
        );
    }
}