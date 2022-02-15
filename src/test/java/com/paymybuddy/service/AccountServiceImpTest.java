package com.paymybuddy.service;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.paymybuddy.model.Account;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImpTest {
@InjectMocks
AccountServiceImp accountServiceImpl;
	@Test
	public void testSave() {
		BigDecimal accountAmount = new BigDecimal(400);
		Account accountOfAUser = new  Account(1,4,"bank",456789,accountAmount);
		Account account = accountServiceImpl.save(accountOfAUser);
		assertEquals(accountOfAUser,account);
		assertEquals(account.getBankName(),accountOfAUser.getBankName());
	}

	@Test
	public void testGetAccountByUserId() {
	}

}
