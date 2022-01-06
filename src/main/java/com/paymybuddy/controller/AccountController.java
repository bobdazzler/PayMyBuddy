package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.model.Account;
import com.paymybuddy.service.AccountService;
@RestController
public class AccountController {
@Autowired
private AccountService accountService;
@GetMapping("/account")
public ModelAndView viewAccountPage(Model model) {
	model.addAttribute("listAccount", accountService.getAllEmployees());
	return new ModelAndView("account");
	
}
@GetMapping("/newAccountForm")
public ModelAndView showNewAccountForm (Model model) {
	Account account = new Account();
	model.addAttribute("account", account);
	return new ModelAndView("new_account");
}
@PostMapping("/saveAccount")
public ModelAndView saveAccount(@ModelAttribute("account")Account account) {
	accountService.save(account);
	return new ModelAndView("redirect:account");
}
}
