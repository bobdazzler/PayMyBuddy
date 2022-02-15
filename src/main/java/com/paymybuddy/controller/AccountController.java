package com.paymybuddy.controller;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;
@RestController
@SessionAttributes
public class AccountController {
	Logger logger =  LoggerFactory.getLogger(AccountController.class);
	private UserServiceImpl userServiceImpl;
	private AccountServiceImp accountServiceImp;
	@Autowired
	public AccountController(UserServiceImpl userServiceImpl,UserRepository
			userRepository,AccountServiceImp accountServiceImp) {
		this.userServiceImpl=userServiceImpl;
		this.accountServiceImp=accountServiceImp;
	}
	@GetMapping("/account")
	public ModelAndView viewAccountPageOfAUser( Model model, 
			@ModelAttribute("account")Account account,HttpServletRequest request) {

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
			return new ModelAndView("home");
		}
	}
	@PostMapping("/saveAccount")
	public ModelAndView saveAccount(@ModelAttribute("account") AccountDto accountDto, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("userId");
		Account account = new Account(accountDto.getBankName(),accountDto.getBankAccountNumber(),accountDto.getAmount());
		account.setUserId(userId);
		accountServiceImp.save(account);
		return new ModelAndView("redirect:account");
	}
	@GetMapping("/AddMoneyToAccount")
	public ModelAndView showFormForAddingMoneyToAccount( @ModelAttribute("account") Account account) {

		return new ModelAndView("updateAccount");
	}
	@PostMapping("/saveUpdate")
	public ModelAndView addingMoneyToAccount(@ModelAttribute("account") AccountDto accountDto,BindingResult result,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("userId");
		Account userAccount = accountServiceImp.getAccountByUserId(userId);
		userAccount.setAmount(accountDto.getAmount().add(userAccount.getAmount()));
		accountServiceImp.save(userAccount);
		redirectAttributes.addFlashAttribute("message", "you have successfully added money to your "
				+ "account balance please go back to your account");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return new ModelAndView("redirect:/AddMoneyToAccount");
	}
	@GetMapping("/uploadMoney")
	public ModelAndView uploadMoney(@ModelAttribute("user") User user) {
		return new ModelAndView("uploadMoney");
	}
	@PostMapping("/uploadMoneyToBalance")
	public ModelAndView addingMoneyToUserBalance(@ModelAttribute("user") UserDto userDto,BindingResult result,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("userId");
		User loggedInUser = userServiceImpl.getUserById(userId);
		Account userAccount = accountServiceImp.getAccountByUserId(userId);
		logger.info("logged in user account balance "+userAccount.getAmount()+ " amount  from userDto " 
		+ userDto.getBalance());
		if(userAccount.getAmount().compareTo( userDto.getBalance()) == 1) {
			BigDecimal total = loggedInUser.getBalance().add( userDto.getBalance());
			BigDecimal accountTotal = userAccount.getAmount().subtract(userDto.getBalance());
			loggedInUser.setBalance(total);
			userServiceImpl.saveUpdatedUser(loggedInUser);
			userAccount.setAmount(accountTotal);
			accountServiceImp.save(userAccount);
			redirectAttributes.addFlashAttribute("message", "you have successfully added money to"
					+ " your PAYMYBUDDY Balance");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}else {
			redirectAttributes.addFlashAttribute("message", "add money to your account balance");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		return new ModelAndView("redirect:/profile");
	}
}