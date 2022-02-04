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
	public void save(Account account) {
		this.accountRepository.save(account);

	}
	@Override
	public Account getAccountByUserId(int userId) {
		
		return accountRepository.getAccountByUserID(userId);
	}

}
