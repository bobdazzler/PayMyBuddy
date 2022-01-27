package com.paymybuddy.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.UserRepository;
@Service
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public User getUserIdToCreateAccount(int id) {
		Optional < User > optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" accoiunt not found for id :: " + id);
		}
		return user;
	}
	@Transactional
	@Override
	public void save(Account account) {
		this.accountRepository.save(account);

	}

}
