package com.paymybuddy.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.autoconfigure.session.SessionProperties.Servlet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import com.paymybuddy.model.Account;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AccountControllerTest {
	@Mock
	private UserServiceImpl userServiceImpl;
	@Mock
	private UserRepository userRepository;
	@Mock
	private AccountServiceImp accountServiceImpMock;
	@Mock
	Model model;
	MockHttpServletRequest request = new MockHttpServletRequest();
	@InjectMocks
	AccountController accountController ;
	@Before
	public void setUp() {
		when(request.getSession().getAttribute("userId")).thenReturn(1);
	}
	
	@Test
	public void testViewAccountPageOfAUserIfAccoutExist() throws Exception {
		
		Account account = new Account(1,"bank",456789,400);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		ModelAndView modelAndView = accountController.viewAccountPageOfAUser(model,account);
		assertEquals("account", modelAndView.getViewName());
	}

	@Test
	void testSaveAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testShowFormForUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testAddingMoneyToAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testUploadMoney() {
		fail("Not yet implemented");
	}

	@Test
	void testAddingMoneyToUserBalance() {
		fail("Not yet implemented");
	}

}
