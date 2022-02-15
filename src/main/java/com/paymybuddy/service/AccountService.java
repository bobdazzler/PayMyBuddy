package com.paymybuddy.service;
import com.paymybuddy.model.Account;

public interface AccountService {
	public Account save (Account account);
	public Account getAccountByUserId(int userId);
}
