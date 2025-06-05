package com.budgetbloom.app.repository;
import com.budgetbloom.app.model.Transaction;



import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
	
}
