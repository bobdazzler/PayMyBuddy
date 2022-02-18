package com.paymybuddy.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ConnectionRepository;
import com.paymybuddy.repository.UserRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectionServiceImplTest {
	@InjectMocks
	ConnectionServiceImpl connectionServiceImpl;
	@Mock
	ConnectionRepository connectionRepository;
	@Mock
	UserRepository userRepository;

	@Test
	public void testCheckingIfUserExist() {
		BigDecimal userBalance = new BigDecimal(0);
		User user = new User(1,"dazzler","dazzling@gmail.com","0903567877","89384jhd",userBalance);
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
		User foundUser = connectionServiceImpl.checkingIfUserExist("dazzling@gmail.com");
		assertEquals(user,foundUser);
		assertEquals(user.getPassWord(),foundUser.getPassWord());
	}
	
	@Test
	public void testCheckingIfUserIsNull() {

		User user = null;
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
		assertThrows(UsernameNotFoundException.class, () -> connectionServiceImpl.checkingIfUserExist("dazzling@gmail.com"));
	}

	@Test
	public void testListOfConnectedEmail() {
		Connection connection1 = new Connection(1,2,"oghoro@gmail.com");
		Connection connection2 = new Connection(1,3,"dazzler@gmail.com");
		List<Connection> connections = new ArrayList<>();
		connections.add(connection2);
		connections.add(connection1);
		when(connectionRepository.findAll()).thenReturn(connections);
		List<Connection> connection = connectionServiceImpl.listOfConnectedEmail();
			assertEquals(connections,connection);
	}

	@Test
	public void testSaveConnection() {
		Connection connection = new Connection(1,2,"oghoro@gmail.com");
		when(connectionRepository.save(Mockito.any())).thenReturn(connection);
		Connection toBeSavedConnection = connectionServiceImpl.saveConnection(connection);
		assertEquals(connection,toBeSavedConnection);
	}

	
}
