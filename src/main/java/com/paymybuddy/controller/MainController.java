package com.paymybuddy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.model.User;
import com.paymybuddy.service.CustomUserDetails;
import com.paymybuddy.service.UserServiceImpl;

@RestController
public class MainController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/home")
	public ModelAndView home(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetials,
			Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User loggedInUser = userService.getExistingUser(userDetail.getUsername());
		model.addAttribute("loggedInUser", loggedInUser);
		request.getSession().setAttribute("userId", loggedInUser.getId());
		return new ModelAndView("home");
	}
}
