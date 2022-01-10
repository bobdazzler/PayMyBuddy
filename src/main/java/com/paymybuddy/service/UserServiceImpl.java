package com.paymybuddy.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.Role;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;
public UserServiceImpl(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}
	@Override
	public User save(UserRegistrationDto registrationDto) {
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     User user = new User(registrationDto.getFirstName(),registrationDto.getMiddleName(),registrationDto.getLastName(),
    		 registrationDto.getEmail() ,registrationDto.getMobileNumber(),registrationDto.getGender(),encoder.encode(registrationDto.getPassWord()),registrationDto.getDateOfBirth(), Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassWord(), mapRolesToAuthorities(user.getRoles()));		
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
