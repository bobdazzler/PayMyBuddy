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
public class ContactControllerTest {
	@InjectMocks
	ContactController contactController;
	@Test
public void testShowConctactInformation() {
		ModelAndView modelAndView =contactController.showConctactInformation();
		assertEquals("contact",modelAndView.getViewName());
	}

}
