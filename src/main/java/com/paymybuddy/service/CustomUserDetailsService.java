package com.paymybuddy.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.paymybuddy.model.User;
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userServiceImpl.getExistingUser(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		 return new CustomUserDetails(user);
	}
}
