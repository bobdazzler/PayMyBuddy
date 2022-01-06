package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Account;
import com.paymybuddy.repository.AccountRepository;
@Service
public class AccountServiceImp implements AccountService {
	 @Autowired
	    private AccountRepository accountRepository;

	    @Override
	    public List <Account> getAllEmployees() {
	        return accountRepository.findAll();
	    }

		@Override
		public void save(Account account) {
			this.accountRepository.save(account);
			
		}
}
