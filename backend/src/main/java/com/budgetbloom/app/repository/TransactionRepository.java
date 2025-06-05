package com.budgetbloom.app.repository;
import com.budgetbloom.app.model.Transaction;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
	// find all transactions by user id
    List<Transaction> findByUserId(UUID userId);
}
