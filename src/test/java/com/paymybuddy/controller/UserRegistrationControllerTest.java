package com.paymybuddy.controller;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserServiceImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationControllerTest {
	@InjectMocks
	UserRegistrationController userRegistrationController;
	@Mock
	Model model;
	@Mock
	UserRegistrationDto userRegistrationDto;
	@Mock
	RedirectAttributes attribute;
	@Mock
	UserServiceImpl userServiceImplMock;
	@Mock
	BindingResult result;
	@Test
	public void testShowRegisterRegister() {
		ModelAndView modelAndView = userRegistrationController.showRegisterationPage(model);
		assertEquals(modelAndView.getViewName(),"signup_form");
	}

	@Test
	public void testRegisterUserAccountwhenUserIsNull() {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto("oghoro","oghoro@gmail.com","09023456987","weGohere123");
		User user = new User(userRegistrationDto.getUserName(),userRegistrationDto.getEmail(),userRegistrationDto.getMobileNumber(),userRegistrationDto.getPassWord());
		user.setBalance(BigDecimal.ZERO);
		when(userServiceImplMock.getExistingUser(Mockito.anyString())).thenReturn(null);
		when(userServiceImplMock.save(Mockito.any())).thenReturn(user);
		ModelAndView modelAndView = userRegistrationController.registerUserAccount(userRegistrationDto,result,attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/registration");
	
	}
	@Test
	public void testRegisterUserAccountwhenUserExist() {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto("oghoro","oghoro@gmail.com","09023456987","weGohere123");
		User user = new User(userRegistrationDto.getUserName(),userRegistrationDto.getEmail(),userRegistrationDto.getMobileNumber(),userRegistrationDto.getPassWord());
		user.setBalance(BigDecimal.ZERO);
		when(userServiceImplMock.getExistingUser(Mockito.anyString())).thenReturn(user);
		ModelAndView modelAndView = userRegistrationController.registerUserAccount(userRegistrationDto,result,attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/registration");
	
	}

}
