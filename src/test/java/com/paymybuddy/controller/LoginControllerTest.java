package com.paymybuddy.controller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {
	@InjectMocks
	LoginController loginController;
	@Test
	public void indexTest() {
		ModelAndView modelAndView =loginController.index();
		assertEquals("login",modelAndView.getViewName());
	}
	@Test
	public void showLoginTest() {
		ModelAndView modelAndView =loginController.showLogin();
		assertEquals("login",modelAndView.getViewName());
	}
}
