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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileControllerTest {
	@Mock
	RedirectAttributes attribute;
	@Mock
	AccountDto accountDtoMock;
	@Mock
	AccountServiceImp accountServiceImpMock;
	@Mock
	UserServiceImpl userServiceImplMock;
	@Mock
	Model model;
	@Mock
	MockHttpServletRequest request;
	@InjectMocks
	ProfileController profileController;
	@Test
	public void testShowingProfileOfUserWhenRequestIsNull() throws Exception {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
	    Mockito.when(request.getSession()).thenReturn(sessionMock);
	    when(sessionMock.getAttribute("userId")).thenReturn(null);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(null);
		ModelAndView modelAndView = profileController.showingProfileOfUser(model,request);
		assertEquals("home", modelAndView.getViewName());
	}
	@Before
	public void setUp() {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
	    Mockito.when(request.getSession()).thenReturn(sessionMock);
	    when(sessionMock.getAttribute("userId")).thenReturn(4);
	}
	@Test
	public void testShowingProfileOfUserwhenRequestIsNotNull() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(user);
		ModelAndView modelAndView = profileController.showingProfileOfUser(model,request);
		assertEquals("/profile", modelAndView.getViewName());
	}

	@Test
	public void testSendingMoneyToAccountFromUserBalanceWhenAccountExistAndBalanceMore() {
		BigDecimal userBalance = new BigDecimal(1000);
		BigDecimal expectedUserBalance = new BigDecimal(900);
		BigDecimal expectedAccountAmout = new BigDecimal(500);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		 BigDecimal accountAmount = new BigDecimal(400);
		Account account = new  Account(1,"bank",456789,accountAmount);
		BigDecimal amountToBeSent = new BigDecimal(100);
		when(accountDtoMock.getAmount()).thenReturn(amountToBeSent);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(user);
		ModelAndView modelAndView = profileController.sendingMoneyToAccountFromUserBalance(accountDtoMock, request, null,attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/profile");
		assertEquals(user.getBalance(),expectedUserBalance);
		assertEquals(account.getAmount(),expectedAccountAmout);
	}
	@Test
	public void testSendingMoneyToAccountFromUserBalanceWhenAccountDoesNotExist() {
		BigDecimal userBalance = new BigDecimal(1000);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		Account account = null;
		BigDecimal amountToBeSent = new BigDecimal(100);
		when(accountDtoMock.getAmount()).thenReturn(amountToBeSent);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(user);
		ModelAndView modelAndView = profileController.sendingMoneyToAccountFromUserBalance(accountDtoMock, request, null,attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/profile");
		assertEquals(user.getBalance(),userBalance);
	}
	@Test
	public void testSendingMoneyToAccountFromUserBalanceWhenUserBalanceIsLess() {
		BigDecimal userBalance = new BigDecimal(100);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		 BigDecimal accountAmount = new BigDecimal(400);
		Account account = new  Account(1,"bank",456789,accountAmount);
		BigDecimal amountToBeSent = new BigDecimal(1000);
		when(accountDtoMock.getAmount()).thenReturn(amountToBeSent);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(user);
		ModelAndView modelAndView = profileController.sendingMoneyToAccountFromUserBalance(accountDtoMock, request, null,attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/profile");
		assertEquals(user.getBalance(),userBalance);
		assertEquals(account.getAmount(),accountAmount);
	}

	@Test
	public void testViewsendMoneyToAccount() {
		ModelAndView modelAndView = profileController.viewsendMoneyToAccount(accountDtoMock);
		assertEquals(modelAndView.getViewName(),"uploadToAccount");
	}

}
