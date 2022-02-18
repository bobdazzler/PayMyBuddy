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
import com.paymybuddy.model.Account;
import com.paymybuddy.repository.AccountRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImpTest {
@InjectMocks
AccountServiceImp accountServiceImpl;
@Mock
AccountRepository accountRepository;
	@Test
	public void testSave() {
		BigDecimal accountAmount = new BigDecimal(400);
		Account accountOfAUser = new  Account(1,4,"bank",456789,accountAmount);
		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(accountOfAUser);
		Account account = accountServiceImpl.save(accountOfAUser);
		assertEquals(accountOfAUser,account);
		assertEquals(account.getBankName(),accountOfAUser.getBankName());
	}

	@Test
	public void testGetAccountByUserId() {
		BigDecimal accountAmount = new BigDecimal(400);
		Account accountByIdReturned = new  Account(1,4,"bank",456789,accountAmount);
		when(accountRepository.getAccountByUserID(Mockito.anyInt())).thenReturn(accountByIdReturned);
		Account account = accountServiceImpl.getAccountByUserId(1);
		assertEquals(accountByIdReturned,account);
		assertEquals(account.getBankName(),accountByIdReturned.getBankName());
	}

}
