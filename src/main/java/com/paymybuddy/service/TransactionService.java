package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.model.Transaction;

public interface TransactionService {
List<Transaction> findTransaction();
}
