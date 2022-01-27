package com.paymybuddy.service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ConnectionRepository;
import com.paymybuddy.repository.UserRepository;

@Service
public class ConnectionServiceImpl implements ConnectionService{
	Logger logger =  LoggerFactory.getLogger(ConnectionServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConnectionRepository connectionRepository;
	@Transactional
	@Override
	public User checkingIfUserExist(String userName) {
		User recieverDetails = null;
		try {
		recieverDetails = userRepository.findByEmail(userName);
		}
		catch(UsernameNotFoundException ex) {
			logger.error(" user not found"+ex);
		}
		return recieverDetails;
	}
	@Override
	public Integer gettingRecieverId(String userName) {
		User recieverDetails =checkingIfUserExist(userName);
		return recieverDetails.getId();
	}
	@Override
	public List<Connection> listOfConnectedEmail() {	
		logger.info("connection list"+connectionRepository.findAll().toString());
		return connectionRepository.findAll();
	}
	
}
