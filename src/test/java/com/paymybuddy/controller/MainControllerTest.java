package com.paymybuddy.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import com.paymybuddy.model.User;
import com.paymybuddy.service.CustomUserDetails;
import com.paymybuddy.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {

	@Mock
	MockHttpServletRequest request;
	@InjectMocks
	MainController mainController;
	@Mock
	UserServiceImpl userServiceMock;
	@Mock
	CustomUserDetails customUserDetials;
	@Mock
	Model model;

	@Test
	public void testHome() {
		BigDecimal userBalance = new BigDecimal(0);
		User userToBeLogIn = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userId")).thenReturn(1);
		UserDetails userDetails = Mockito.mock(UserDetails.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when((UserDetails)authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn("dazzling@gmail.com");
		when(userServiceMock.getExistingUser(Mockito.any())).thenReturn(userToBeLogIn);
		ModelAndView modelAndView = mainController.home(request, customUserDetials,model);
		assertEquals("home",modelAndView.getViewName());	
	}

}
