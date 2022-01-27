package com.paymybuddy.service;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;

public interface AccountService {
	public void save (Account account);
	User getUserIdToCreateAccount(int id);
}
