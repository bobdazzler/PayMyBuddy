package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.repository.TransactionRepository;

public class TransactionImpl implements TransactionService {
	@Autowired
	TransactionRepository transactionRepository;
	@Override
	public List<Transaction> findTransaction() {
		return transactionRepository.findAll();
	}

}
