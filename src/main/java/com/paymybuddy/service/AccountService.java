package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.model.Account;

public interface AccountService {
	List<Account> getAllEmployees();
	public void save (Account account);
}
