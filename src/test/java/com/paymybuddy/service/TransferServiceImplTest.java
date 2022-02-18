package com.paymybuddy.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paymybuddy.model.Transfer;
import com.paymybuddy.repository.TransferRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferServiceImplTest {
	@InjectMocks
	TransferServiceImpl transferServiceImpl;
	@Mock
	TransferRepository transferRepository;
	@Test
	public void testSave() {
		BigDecimal bigDecimal = new BigDecimal(100);
		BigDecimal bigDecimalCharges = new BigDecimal(0.5);
		Transfer transfer = new Transfer(bigDecimal,"do@gmail.com",bigDecimalCharges,1,2);
		when(transferRepository.save(transfer)).thenReturn(transfer);
		Transfer transferOfAUser = transferServiceImpl.save(transfer);
		assertEquals(transferOfAUser,transfer);
	}

}
