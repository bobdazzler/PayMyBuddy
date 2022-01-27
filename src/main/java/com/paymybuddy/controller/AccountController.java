package com.paymybuddy.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class AccountController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	private AccountServiceImp accountServiceImp;
	@GetMapping("/account")
	public ModelAndView viewAccountPageOfAUser( Model model,  HttpServletRequest request, @ModelAttribute("account") Account account) {
		int userId = (int) request.getSession().getAttribute("userId");
		List<Account> userAccounts = accountRepository.findAll();
		for(Account userAccount : userAccounts) {
			if (userAccount.getUser_id() == userId) {
				model.addAttribute("accountOfUser", userAccount);
				return new ModelAndView("account");
			}
		}
		return new ModelAndView("new_account");	
	}
	@PostMapping("/saveAccount")
	public ModelAndView saveAccount(@ModelAttribute("account")Account account, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("userId");
		account.setUser_id(userId);
		accountServiceImp.save(account);
		return new ModelAndView("redirect:account");
	}
	@GetMapping("/AddMoneyToAccount")
	public ModelAndView showFormForUpdate( @ModelAttribute("account") Account account, HttpServletRequest request) {

		return new ModelAndView("updateAccount");
	}
	@PostMapping("/saveUpdate")
	public ModelAndView addingMoneyToAccount(@ModelAttribute("account") Account account,HttpServletRequest request,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		for(Account userAccount:accountRepository.findAll()) {
			if(userAccount.getUser_id() == userId) {
				double currentAccountBalance = userAccount.getAmount();
				userAccount.setAmount(account.getAmount() + currentAccountBalance);
				accountServiceImp.save(userAccount);

			}
		}
		redirectAttributes.addFlashAttribute("message", "you have successfully added money to your account balance please go back to your account");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return new ModelAndView("redirect:/AddMoneyToAccount");
	}
	@GetMapping("/uploadMoney")
	public ModelAndView uploadMoney(@ModelAttribute("user") User user) {
		return new ModelAndView("uploadMoney");
	}
	@PostMapping("/uploadMoneyToBalance")
	public ModelAndView addingMoneyToUserBalance(@ModelAttribute("user")User user,HttpServletRequest request,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		for(User loggedInUser: userRepository.findAll() ) {	
			for(Account userAccount:accountRepository.findAll()) {
				if(loggedInUser.getId()==userId && userAccount.getUser_id() == userId ) {
					double total = loggedInUser.getBalance()+user.getBalance();
					double accountTotal = userAccount.getAmount()-user.getBalance();
					loggedInUser.setBalance(total);
					userServiceImpl.save(loggedInUser);
					userAccount.setAmount(accountTotal);
					accountServiceImp.save(userAccount);
				}
			}
		}
		redirectAttributes.addFlashAttribute("message", "you have successfully added money to your PAYMYBUDDY Balance");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return new ModelAndView("redirect:/profile");
	}
	
}