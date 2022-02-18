package com.paymybuddy.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRepository userRepository;
	@Test
	public void testSave() {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto("oghoro","oghoro@gmail.com","09023456987","weGohere123");
		User user = new User(userRegistrationDto.getUserName(),userRegistrationDto.getEmail(),userRegistrationDto.getMobileNumber(),userRegistrationDto.getPassWord());
		user.setBalance(BigDecimal.ZERO);
		when(userRepository.save(Mockito.any())).thenReturn(user);
		User userToBeSaved = userServiceImpl.save(userRegistrationDto);
		assertEquals(userToBeSaved.getPassWord(),user.getPassWord());
		
	}
	@Test
	public void testGetExistingUser() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
		User userFoundByEmail = userServiceImpl.getExistingUser("dazzling@gmail.com");
		assertEquals(userFoundByEmail,user);
	}

	@Test
	public void testSaveUpdatedUser() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userRepository.save(Mockito.any())).thenReturn(user);
		User userUpdatedField = userServiceImpl.saveUpdatedUser(user);
		assertEquals(userUpdatedField,user);
	}

	@Test
	public void testGetUserById() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userRepository.getUserById(Mockito.anyInt())).thenReturn(user);
		User foundUser = userServiceImpl.getUserById(1);
		assertEquals(foundUser,user);
	}

}
