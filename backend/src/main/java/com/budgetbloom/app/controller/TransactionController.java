package com.budgetbloom.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.budgetbloom.app.model.Transaction;

import com.budgetbloom.app.service.TransactionService;

@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @RequestMapping
    // get all transactions
    public List<Transaction> getallTransactions() {
        List<Transaction> alltransactions = transactionService.getAllTransactions();
        return alltransactions;
    }

    // get all transactions by user id
    @RequestMapping("/user/{userId}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable String userId) {
        List<Transaction> userTransactions = transactionService.getTransactionsByUserId(userId);
        if (userTransactions.isEmpty()) {
            return ResponseEntity.status(404).body(false);
        }
        return ResponseEntity.ok(userTransactions);
    }

    // add a new transaction
    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        boolean addedtransaction = transactionService.addTransaction(transaction);
        if (!addedtransaction) {
            ResponseEntity.status(404).body(false);
        }
        return ResponseEntity.status(201).body(addedtransaction);
    }
}
