package com.paymybuddy.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserService;

@RestController
public class UserRegistrationController {
private UserService userService;

public UserRegistrationController(UserService userService) {
	super();
	this.userService = userService;
}
@GetMapping("/registration") 
public ModelAndView register(Model model) {
	model.addAttribute("user", new User());

	return new ModelAndView  ("signup_form"); 
} 
@PostMapping("/save")
public ModelAndView registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
	userService.save(registrationDto);
	return new ModelAndView("signupSuccess");
	
}
}
