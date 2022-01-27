package com.paymybuddy.service;
import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;

public interface UserService  {
User save(UserRegistrationDto registrationDto);
}
