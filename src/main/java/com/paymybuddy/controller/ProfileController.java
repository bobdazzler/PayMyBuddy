package com.paymybuddy.controller;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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
import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.model.Account;
import com.paymybuddy.model.User;
import com.paymybuddy.service.AccountServiceImp;
import com.paymybuddy.service.UserServiceImpl;
@RestController
public class ProfileController {
	Logger logger =  LoggerFactory.getLogger(ProfileController.class);
	@Autowired
	private AccountServiceImp accountServiceImp;
	@Autowired
	UserServiceImpl userServiceImpl;
	@GetMapping("/profile")
	public ModelAndView showingProfileOfUser(Model model, HttpServletRequest request ) {
		if(request.getSession().getAttribute("userId")!= null) {
			int userId = (int) request.getSession().getAttribute("userId");
			User user = userServiceImpl.getUserById(userId);
			model.addAttribute("userName",user.getUserName() );
			model.addAttribute("balance",user.getBalance());
			return new ModelAndView("/profile");
		}else {
			return new ModelAndView("home");
		}
	}
	@Transactional
	@PostMapping("/saveMoneyToAccount")
	public ModelAndView sendingMoneyToAccountFromUserBalance(@ModelAttribute("account") AccountDto accountDto,HttpServletRequest request,BindingResult result,
			RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");
		User user = userServiceImpl.getUserById(userId);
		Account accountOfUser = accountServiceImp.getAccountByUserId(userId);
			if(accountOfUser!=null) {
				if( user.getBalance().compareTo(accountDto.getAmount()) == 1) {
				BigDecimal userBalance = user.getBalance().subtract(accountDto.getAmount());
				user.setBalance(userBalance);
				userServiceImpl.saveUpdatedUser(user);
				BigDecimal accountTotalAmout =	accountOfUser.getAmount().add(accountDto.getAmount());
				accountOfUser.setAmount(accountTotalAmout);
				accountServiceImp.save(accountOfUser);
				redirectAttributes.addFlashAttribute("message", "you have successfully sent money to your bank Account");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				}else {
					redirectAttributes.addFlashAttribute("message", "Balance is low");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				}
			}else {
				redirectAttributes.addFlashAttribute("message", "please go through the process of uploading money to balance to create an account ");
				redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			}
		return new ModelAndView("redirect:/profile");
	}
	@GetMapping("/sendMoneyToAccount")
	public ModelAndView viewsendMoneyToAccount(@ModelAttribute("account") AccountDto accountDto) {
		return new ModelAndView("uploadToAccount");
	}
}
