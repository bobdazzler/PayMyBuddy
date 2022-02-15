package com.paymybuddy.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paymybuddy.model.Account;
import com.paymybuddy.repository.AccountRepository;
@Service
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Transactional
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}
	@Override
	public Account getAccountByUserId(int userId) {
		
		return accountRepository.getAccountByUserID(userId);
	}

}
