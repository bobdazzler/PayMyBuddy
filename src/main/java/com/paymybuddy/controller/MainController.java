package com.paymybuddy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.model.User;
import com.paymybuddy.service.UserServiceImpl;

@RestController
public class MainController {
	@Autowired
private	UserServiceImpl userService;
@GetMapping("/home")
public ModelAndView home(HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    model.setViewName("home");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    User u = userService.getExistingUser(userDetail.getUsername());
    request.getSession().setAttribute("userId", u.getId());
    return model;
}
}
