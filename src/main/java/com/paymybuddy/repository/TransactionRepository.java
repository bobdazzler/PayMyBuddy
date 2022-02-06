package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.paymybuddy.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	 @Query("SELECT transaction FROM Transaction transaction WHERE transaction.userId = ?1")
		Transaction getTransactionByUserID(int userID);
}
