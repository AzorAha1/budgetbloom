package com.budgetbloom.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbloom.app.model.Transaction;
import com.budgetbloom.app.repository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        List <Transaction> allTransactions = transactionRepository.findAll();
        return allTransactions;
    }

    public List<Transaction> getTransactionsByUserId(String userId) {
        UUID uuid = UUID.fromString(userId);
        List<Transaction> userTransactions = transactionRepository.findByUserId(uuid);
        return userTransactions;

    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionRepository.save(transaction);
            return true; // Transaction added successfully
        }
        return false; // Transaction is null, not added
    }

    
}
