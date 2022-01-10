package com.paymybuddy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ContactController {
@GetMapping("/contact")
public ModelAndView showConctactInformation() {
	return new ModelAndView("contact");
}
}
