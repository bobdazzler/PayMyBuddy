package com.paymybuddy.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.ConnectionDto;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.service.ConnectionServiceImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectionControllerTest {
	@InjectMocks
	ConnectionController connectionController;
	@Mock
	ConnectionServiceImpl connectionServiceImplMock;
	@Mock
	MockHttpServletRequest request;
	@Mock
	RedirectAttributes attribute;
	@Mock
	Connection connection;
	@Mock
	Model model;
	@Mock 
	BindingResult result;
	@Mock
	ConnectionDto connectionDto;
	@Before
	public void setUp() {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userId")).thenReturn(4);
	}
	@Test
	public void getConnectionTest() {
		ModelAndView modelAndView = connectionController.getConnection(connectionDto);
		assertEquals("connection",modelAndView.getViewName());
	}
	@Test
	public void saveConnectionTestWhenUserExist() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(connectionDto.getRecieverEmail()).thenReturn("dazzling@gmail.com");
		when(connectionServiceImplMock.checkingIfUserExist(Mockito.anyString())).thenReturn(user);
		ModelAndView modelAndView = connectionController.saveConnection(connectionDto,request,result, attribute, model);
		assertEquals("redirect:transfer",modelAndView.getViewName());	
	}
	@Test
	public void saveConnectionTestWhenUserDoesNotExist() {
		User user = null;
		when(connectionServiceImplMock.checkingIfUserExist(Mockito.anyString())).thenReturn(user);
		ModelAndView modelAndView = connectionController.saveConnection(connectionDto,request,result, attribute, model);
		assertEquals("redirect:connection",modelAndView.getViewName());
	}
}
