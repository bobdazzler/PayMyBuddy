package com.paymybuddy.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paymybuddy.model.Transaction;
import com.paymybuddy.repository.TransactionRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceImplTest {
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	@Mock
	TransactionRepository transactionRepository;
	@Test
	public void testFindTransaction() {
		BigDecimal bigDecimal = new BigDecimal(100);
		BigDecimal bigDecimalCharges = new BigDecimal(0.5);
		Transaction transaction1 = new Transaction(bigDecimal,"Ejeokes@gmail.com",bigDecimalCharges,1,2,"enjoy your self");
		Transaction transaction2 = new Transaction(bigDecimal,"lol@gmail.com",bigDecimalCharges,1,2,"enjoy your self");
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction2);
		transactions.add(transaction1);
		when(transactionRepository.findAll()).thenReturn(transactions);
		List<Transaction> transactionsOfAUser = transactionServiceImpl.findTransaction();
		assertEquals(transactionsOfAUser,transactions);	
	}

	@Test
	public void testSaveUserTransaction() {
		BigDecimal bigDecimal = new BigDecimal(100);
		BigDecimal bigDecimalCharges = new BigDecimal(0.5);
		Transaction transaction1 = new Transaction(bigDecimal,"Ejeokes@gmail.com",bigDecimalCharges,1,2,"enjoy your self");
		when(transactionRepository.save(Mockito.any())).thenReturn(transaction1);
		Transaction transactionToBeSaved = transactionServiceImpl.saveUserTransaction(transaction1); 
		assertEquals(transactionToBeSaved,transaction1);
	}

}
