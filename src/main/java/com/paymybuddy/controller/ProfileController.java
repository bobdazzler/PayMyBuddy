package com.paymybuddy.controller;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;

@RestController
public class ProfileController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	private AccountServiceImp accountServiceImp;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserRepository userRepository;
	@GetMapping("/profile")
	public ModelAndView showingProfileOfUser(Model model, HttpServletRequest request ) {
		int userId = (int) request.getSession().getAttribute("userId");
		User user = userRepository.getUserById(userId);
		model.addAttribute("userName",user.getUserName() );
		model.addAttribute("balance",user.getBalance());
		return new ModelAndView("/profile");
	}
	@Transactional
	@PostMapping("/saveMoneyToAccount")
	public ModelAndView sendingMoneyToAccount(@ModelAttribute("account") Account account,HttpServletRequest request,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		User user = userRepository.getUserById(userId);
		Account accountOfUser = accountRepository.getAccountByUserID(userId);
		if(accountOfUser!=null) {
		double userBalance = user.getBalance()-account.getAmount();
		user.setBalance(userBalance);
		userServiceImpl.save(user);
		double accountTotalAmout =	accountOfUser.getAmount()+account.getAmount();
		accountOfUser.setAmount(accountTotalAmout);
		accountServiceImp.save(accountOfUser);
		redirectAttributes.addFlashAttribute("message", "you have successfully sent money to your bank Account");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}else {
		redirectAttributes.addFlashAttribute("message", "please go through the process of uploading money to balance to create an account ");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return new ModelAndView("redirect:/profile");
	}
	@GetMapping("/sendMoneyToAccount")
	public ModelAndView viewsendMoneyToAccount(@ModelAttribute("account") Account account) {
		return new ModelAndView("uploadToAccount");
	}
}