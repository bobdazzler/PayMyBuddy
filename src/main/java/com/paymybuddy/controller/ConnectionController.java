package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.service.ConnectionServiceImpl;

@RestController
public class ConnectionController {
@Autowired
private ConnectionServiceImpl connectionServiceImpl;
@GetMapping("/transfer")
public ModelAndView showTransfer() {
	return new ModelAndView("transfer");
}

@PostMapping("/sendMoney")
public ModelAndView sendMoney(@ModelAttribute("connection") Connection connection) {
	User user =connectionServiceImpl.checkingIfUserExist(connection.getRecieverEmail());
	
	return new ModelAndView("transactionDetails");
}
}
