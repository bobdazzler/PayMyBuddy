package com.paymybuddy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.repository.TransactionRepository;
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	@Override
	public List<Transaction> findTransaction() {
		return transactionRepository.findAll();
	}
	@Transactional
	public Transaction findTransactionByUserId(int UserId) {
		return transactionRepository.getTransactionByUserID(UserId);
	}
	@Transactional
	public Transaction saveUserTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

}
