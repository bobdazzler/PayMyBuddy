package com.paymybuddy.service;

import java.util.List;
import java.util.Optional;

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

		@Override
		public Account getAccountById(int id) {
			        Optional < Account > optional = accountRepository.findById(id);
			        Account account = null;
			        if (optional.isPresent()) {
			            account = optional.get();
			        } else {
			            throw new RuntimeException(" Employee not found for id :: " + id);
			        }
			        return account;
		}
}
