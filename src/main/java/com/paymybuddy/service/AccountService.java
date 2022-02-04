package com.paymybuddy.service;
import com.paymybuddy.model.Account;

public interface AccountService {
	public void save (Account account);
	public Account getAccountByUserId(int userId);
}
