package com.paymybuddy.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.paymybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomUserDetailsServiceTest {
	@InjectMocks
	CustomUserDetailsService customUserDetailsService;
	@Mock
	UserServiceImpl userServiceImpl;
	@Test
	public void loadUserByUserNameTestWhenUserExist() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userServiceImpl.getExistingUser(Mockito.anyString())).thenReturn(user);
		UserDetails userDetails= customUserDetailsService.loadUserByUsername("dazzling@gmail.com");
		assertEquals(userDetails.getUsername(),user.getEmail());
		
	}
	@Test 
	public void loadUserByUserNameTestWhenUserIsNull() {
			User user = null;
			when(userServiceImpl.getExistingUser(Mockito.anyString())).thenReturn(user);
			assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("dazzling@gmail.com"));
		
	}
}