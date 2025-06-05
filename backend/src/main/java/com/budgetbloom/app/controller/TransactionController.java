package com.budgetbloom.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
