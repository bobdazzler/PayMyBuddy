package com.paymybuddy.controller;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;
@RestController
public class AccountController {
	Logger logger =  LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountServiceImp accountServiceImp;
	@Autowired
	HttpServletRequest request;
	@GetMapping("/account")
	public ModelAndView viewAccountPageOfAUser( Model model, 
			@ModelAttribute("account")Account account) {

		if (request.getSession().getAttribute("userId")!=null) {
			Integer userId  =(int)request.getSession().getAttribute("userId");
			Account userAccount = accountServiceImp.getAccountByUserId(userId);
			if(userAccount!=null) {
				model.addAttribute("accountOfUser", userAccount);
				return new ModelAndView("account");
			}else {
				return new ModelAndView("new_account");	
			}	
		}else {
			return new ModelAndView("login");
		}
	}
	@PostMapping("/saveAccount")
	public ModelAndView saveAccount(@ModelAttribute("account")Account account) {
		int userId = (int) request.getSession().getAttribute("userId");
		account.setUserId(userId);
		accountServiceImp.save(account);
		return new ModelAndView("redirect:account");
	}
	@GetMapping("/AddMoneyToAccount")
	public ModelAndView showFormForUpdate( @ModelAttribute("account") Account account) {

		return new ModelAndView("updateAccount");
	}
	@PostMapping("/saveUpdate")
	public ModelAndView addingMoneyToAccount(@ModelAttribute("account") Account account,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		Account userAccount = accountServiceImp.getAccountByUserId(userId);
		double currentAccountBalance = userAccount.getAmount();
		userAccount.setAmount(account.getAmount() + currentAccountBalance);
		accountServiceImp.save(userAccount);
		redirectAttributes.addFlashAttribute("message", "you have successfully added money to your account balance please go back to your account");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return new ModelAndView("redirect:/AddMoneyToAccount");
	}
	@GetMapping("/uploadMoney")
	public ModelAndView uploadMoney(@ModelAttribute("user") User user) {
		return new ModelAndView("uploadMoney");
	}
	@PostMapping("/uploadMoneyToBalance")
	public ModelAndView addingMoneyToUserBalance(@ModelAttribute("user")User user,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		User loggedInUser = userRepository.getUserById(userId);
		Account userAccount = accountServiceImp.getAccountByUserId(userId);
		double total = loggedInUser.getBalance()+user.getBalance();
		double accountTotal = userAccount.getAmount()-user.getBalance();
		loggedInUser.setBalance(total);
		userServiceImpl.save(loggedInUser);
		userAccount.setAmount(accountTotal);
		accountServiceImp.save(userAccount);
		redirectAttributes.addFlashAttribute("message", "you have successfully added money to your PAYMYBUDDY Balance");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return new ModelAndView("redirect:/profile");
	}
}