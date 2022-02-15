package com.paymybuddy.controller;
import static org.junit.jupiter.api.Assertions.*;
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
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {
	@Mock
	RedirectAttributes attribute;
	@Mock
	private UserServiceImpl userServiceImplMock;
	@Mock
	private AccountServiceImp accountServiceImpMock;
	@Mock
	Model model;
	@Mock
	MockHttpServletRequest request;
	@InjectMocks
	AccountController accountController;
	@Mock
	AccountDto accountDto;
	@Mock
	UserDto userDtoMock;
	@Mock
	Account accountMocked;
	@Test
	public void testViewAccountPageOfAUserWhenRequestIsNull() throws Exception {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
	    Mockito.when(request.getSession()).thenReturn(sessionMock);
	    when(sessionMock.getAttribute("userId")).thenReturn(null);
	    BigDecimal amount = new BigDecimal(400);
		Account account = new Account(4,"bank",456789,amount);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		ModelAndView modelAndView = accountController.viewAccountPageOfAUser(model,account,request);
		assertEquals("home", modelAndView.getViewName());
	}
	@Before
	public void setUp() {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
	    Mockito.when(request.getSession()).thenReturn(sessionMock);
	    when(sessionMock.getAttribute("userId")).thenReturn(4);
	}
	@Test
	public void testViewAccountPageOfAUserIfAccoutExist() throws Exception {
		 BigDecimal amount = new BigDecimal(400);
		Account account = new Account(4,"bank",456789,amount);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		ModelAndView modelAndView = accountController.viewAccountPageOfAUser(model,account,request);
		assertEquals("account", modelAndView.getViewName());
	}
	@Test
	public void testViewAccountPageOfAUserIfAccoutDoesNotExist() throws Exception {
		
		Account account = null;
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		ModelAndView modelAndView = accountController.viewAccountPageOfAUser(model,account,request);
		assertEquals("new_account", modelAndView.getViewName());
	}
	
	@Test
	public void testSaveAccount() {
		 BigDecimal amount = new BigDecimal(400);
		Account account = new Account(4,4,"bank",456789,amount);
		when(accountServiceImpMock.save(Mockito.any())).thenReturn(account);
		ModelAndView modelAndView = accountController.saveAccount(accountDto,request);
		assertEquals("redirect:account", modelAndView.getViewName());
	}

	@Test
	public void testShowFormForUpdate() {
		 BigDecimal amount = new BigDecimal(400);
		Account account = new Account(4,"bank",456789,amount);
		ModelAndView modelAndView = accountController.showFormForAddingMoneyToAccount(account);
		assertEquals("updateAccount",modelAndView.getViewName());
	}

	@Test
	public void testAddingMoneyToAccount() {
		 BigDecimal amount = new BigDecimal(400);
		Account account = new  Account(4,"bank",456789,amount);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(account);
		BigDecimal returnedAmount = new BigDecimal(100); 
		when(accountDto.getAmount()).thenReturn(returnedAmount);
		ModelAndView modelAndView = accountController.addingMoneyToAccount(accountDto,null, attribute, request);
		assertEquals("redirect:/AddMoneyToAccount",modelAndView.getViewName());
		BigDecimal expected = new BigDecimal(500);
		assertEquals(account.getAmount(),expected);	
	}

	@Test
	public void testUploadMoney() {
		 BigDecimal amount = new BigDecimal(00.0);
		User user = new User("dazzler","dazzling@gmail.com","0903567877","89384jhd",amount);
		ModelAndView modelAndView = accountController.uploadMoney(user);
		assertEquals("uploadMoney",modelAndView.getViewName());
	}

	@Test
	public void testAddingMoneyToUserBalance() {
		 BigDecimal amount = new BigDecimal(0.00);
		 BigDecimal returnedAmount = new BigDecimal(10);
		 BigDecimal accountAmount = new BigDecimal(400);
		User userLoggedIn = new User("dazzler","dazzling@gmail.com","0903567877","89384jhd",amount);
		Account accountOfUserLoggedIn = new  Account(4,"bank",456789,accountAmount);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(userLoggedIn);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(accountOfUserLoggedIn);
		when(userDtoMock.getBalance()).thenReturn(returnedAmount);
		ModelAndView modelAndView = accountController.addingMoneyToUserBalance(userDtoMock, null, attribute, request);
		BigDecimal expected = new BigDecimal(390);
		BigDecimal expectedUserBalance = new BigDecimal(10);
		assertEquals(accountOfUserLoggedIn.getAmount(),expected);
		assertEquals(userLoggedIn.getBalance(),expectedUserBalance);
		assertEquals("redirect:/profile",modelAndView.getViewName());
	}
	@Test
	public void testAddingMoneyToUserBalanceWhenAccountBalanceIsLess() {
		 BigDecimal amount = new BigDecimal(0.00);
		 BigDecimal returnedAmount = new BigDecimal(1000);
		 BigDecimal accountAmount = new BigDecimal(400);
		User userLoggedIn = new User("dazzler","dazzling@gmail.com","0903567877","89384jhd",amount);
		Account accountOfUserLoggedIn = new  Account(4,"bank",456789,accountAmount);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(userLoggedIn);
		when(accountServiceImpMock.getAccountByUserId(Mockito.anyInt())).thenReturn(accountOfUserLoggedIn);
		when(userDtoMock.getBalance()).thenReturn(returnedAmount);
		ModelAndView modelAndView = accountController.addingMoneyToUserBalance(userDtoMock, null, attribute, request);
		assertEquals(accountOfUserLoggedIn.getAmount(),accountAmount);
		assertEquals(userLoggedIn.getBalance(),amount);
		assertEquals("redirect:/profile",modelAndView.getViewName());
	}
}
