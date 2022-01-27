package com.paymybuddy.service;
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
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	@Transactional 
	@Override
	public User save(UserRegistrationDto registrationDto ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User(registrationDto.getUserName(),registrationDto.getEmail() ,registrationDto.getMobileNumber(),
				encoder.encode(registrationDto.getPassWord()),registrationDto.getBalance());
		return userRepository.save(user);
	}
	@Transactional
	public User getExistingUser(String email) {
		return userRepository.findByEmail(email);
	}
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
		
	}
	

}
