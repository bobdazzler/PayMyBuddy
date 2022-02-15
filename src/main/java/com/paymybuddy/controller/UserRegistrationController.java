package com.paymybuddy.controller;
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
import com.paymybuddy.dto.UserRegistrationDto;
import com.paymybuddy.model.User;
import com.paymybuddy.service.UserServiceImpl;

@RestController
public class UserRegistrationController {
	Logger logger =  LoggerFactory.getLogger(UserRegistrationController.class);
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/registration") 
	public ModelAndView showRegisterationPage(Model model) {
		model.addAttribute("user", new User());
		return new ModelAndView  ("signup_form"); 
	} 
	@PostMapping("/save")
	public ModelAndView registerUserAccount( @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if( userServiceImpl.getExistingUser(registrationDto.getEmail()) != null){
			redirectAttributes.addFlashAttribute("message", "email used by another user");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			result.rejectValue("email", "error.email", "You cannot use this username!");
			return new ModelAndView("redirect:/registration");
		}
		userServiceImpl.save(registrationDto);
		redirectAttributes.addFlashAttribute("message", "you have successfully registered please click login");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return new ModelAndView("redirect:/registration");

	}

}
