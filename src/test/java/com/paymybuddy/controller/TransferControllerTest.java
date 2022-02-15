package com.paymybuddy.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import com.paymybuddy.dto.TransferDto;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.Transfer;
import com.paymybuddy.model.User;
import com.paymybuddy.service.ConnectionServiceImpl;
import com.paymybuddy.service.TransactionServiceImpl;
import com.paymybuddy.service.TransferServiceImpl;
import com.paymybuddy.service.UserServiceImpl;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferControllerTest {
	@InjectMocks
	TransferController transferController;
	@Mock
	MockHttpServletRequest request;
	@Mock
	Transfer transferMock;
	@Mock
	Model model;
	@Mock
	TransactionServiceImpl transactionServiceImplMock;
	@Mock
	ConnectionServiceImpl connectionServiceImplMock;
	@Mock
	UserServiceImpl userServiceImplMock;
	@Mock
	TransferDto transferDtoMock;
	@Mock
	RedirectAttributes attribute;
	@Mock
	User user;
	@Mock
	TransferServiceImpl transferServiceImplMock;

	@Test
	public void testShowTransferwhenHttpRequestIsNUll() {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userId")).thenReturn(null);
		ModelAndView modelAndView = transferController.showTransfer(transferMock, model, request);
		assertEquals(modelAndView.getViewName(),"home");
	}
	@Before
	public void setUp() {
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userId")).thenReturn(4);
	}
	@Test
	public void testShowTransfer() {
		BigDecimal amountSent = new BigDecimal(100);
		Transaction transaction = new Transaction(amountSent,"oghorobob@gmail.com",4,"enjoy my guy");
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Connection connection = new Connection(4,5,"oghoroBob@Gmial.com");
		List<Connection> connections = new ArrayList<>();
		connections.add(connection);
		when(connectionServiceImplMock.listOfConnectedEmail()).thenReturn(connections);
		when(transactionServiceImplMock.findTransaction()).thenReturn(transactions);
		ModelAndView modelAndView = transferController.showTransfer(transferMock, model, request);
		assertEquals(modelAndView.getViewName(),"transfer");
	}
	@Test
	public void testSendMoneyWhenBalanceIsMore() {
		BigDecimal userBalance = new BigDecimal(1000);
		BigDecimal recieverUserBalance = new BigDecimal(0);
		BigDecimal transferAmount = new BigDecimal(100);
	//	BigDecimal expectedUserBalance = new BigDecimal(899.499999999999999989591659144139157433528453111648559570312500);
		User loggedInUser = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		User recieverDetails = new User(2,"oghoro","oghoro@gmail.com","0903567875","6ihdhs",recieverUserBalance);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(loggedInUser);
		when(connectionServiceImplMock.checkingIfUserExist(Mockito.anyString())).thenReturn(recieverDetails);
		when(transferDtoMock.getAmount()).thenReturn(transferAmount);
		when(transferDtoMock.getRecieverEmail()).thenReturn("oghoro@gmail.com");
		when(user.getBalance()).thenReturn(userBalance);
		when(transferServiceImplMock.save(Mockito.any())).thenReturn(transferMock);
		ModelAndView modelAndView = transferController.sendMoney(transferDtoMock, request, null, attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/transfer");
		assertEquals(recieverDetails.getBalance(),transferAmount);
	//	assertEquals(loggedInUser.getBalance(),expectedUserBalance);
		
	}
	@Test
	public void testSendMoneyWhenUserBalanceisLess() {
		BigDecimal userBalance = new BigDecimal(1000);
		BigDecimal recieverUserBalance = new BigDecimal(0);
		BigDecimal transferAmount = new BigDecimal(10001);
		User loggedInUser = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		User recieverDetails = new User(2,"oghoro","oghoro@gmail.com","0903567875","6ihdhs",recieverUserBalance);
		when(user.getBalance()).thenReturn(transferAmount);
		when(user.getId()).thenReturn(2);
		when(userServiceImplMock.getUserById(Mockito.anyInt())).thenReturn(loggedInUser);
		when(connectionServiceImplMock.checkingIfUserExist(Mockito.anyString())).thenReturn(recieverDetails);
		when(transferDtoMock.getAmount()).thenReturn(transferAmount);
		ModelAndView modelAndView = transferController.sendMoney(transferDtoMock, request, null, attribute);
		assertEquals(modelAndView.getViewName(),"redirect:/transfer");
		
	}

}
