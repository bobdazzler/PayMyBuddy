package com.paymybuddy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;

public interface UserService extends UserDetailsService {
User save(UserRegistrationDto registrationDto);
}
