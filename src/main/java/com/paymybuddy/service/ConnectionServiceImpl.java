package com.paymybuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ConnectionRepository;
import com.paymybuddy.repository.UserRepository;
@Service
public class ConnectionServiceImpl implements ConnectionService{
	 @Autowired
	    private ConnectionRepository connectionRepository;
//	@Autowired
//	private Connection connection ;
	@Autowired
	private UserRepository userRepository;
	@Override
public User checkingIfUserExist(String username) {
	User user = userRepository.findByEmail(username);
	if(user==null) {
		throw new UsernameNotFoundException("Invalid email address check if the email is correct");
	}
	return user;
}
	public void sendMoneyToAccountIfExist(String userName) {
		
	}
@Override
public List<Connection> getAllConnections() {
		return connectionRepository.findAll();
}
}
