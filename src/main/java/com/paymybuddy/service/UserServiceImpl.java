package com.paymybuddy.service;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Transactional 
	@Override
	public User save(UserRegistrationDto registrationDto ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User(registrationDto.getUserName(),registrationDto.getEmail() ,registrationDto.getMobileNumber(),
				encoder.encode(registrationDto.getPassWord()));
		user.setBalance(BigDecimal.ZERO);
		return userRepository.save(user);
	}
	@Transactional
	public User getExistingUser(String email) {
		return userRepository.findByEmail(email);
	}
	@Transactional
	public User saveUpdatedUser(User user) {
		return userRepository.save(user);	
	}
	@Transactional 
	public User getUserById(int userId) {
		return userRepository.getUserById(userId);
	}
	

}
