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

import com.paymybuddy.dto.ConnectionDto;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.service.ConnectionServiceImpl;
import com.paymybuddy.service.UserServiceImpl;
@RestController
public class ConnectionController {
	Logger logger =  LoggerFactory.getLogger(ConnectionController.class);
	@Autowired
	ConnectionServiceImpl connectionServiceImp;
	@Autowired
	UserServiceImpl userServiceImpl;
	@GetMapping("/connection")
	public ModelAndView getConnection(@ModelAttribute("connection") ConnectionDto connectionDto) {
		return new ModelAndView("connection");
	}
	@PostMapping("/saveConnection")
	public ModelAndView saveConnection(@ModelAttribute("connection") ConnectionDto connectionDto,HttpServletRequest request,
			BindingResult result,RedirectAttributes redirectAttributes, Model model){
		int userId = (int) request.getSession().getAttribute("userId");	
			User user =	connectionServiceImp.checkingIfUserExist(connectionDto.getRecieverEmail());
		if(user!=null) {
			logger.info("connection user found" + user);
			Connection connection = new Connection();
			connection.setConnectedUserId(user.getId());
			connection.setUserId(userId);
			connection.setRecieverEmail(user.getEmail());
			connectionServiceImp.saveConnection(connection);
			return new ModelAndView("redirect:transfer");
		}else {
			result.rejectValue("recieverEmail", "error.recieverEmail", "You cannot use this username!");
		redirectAttributes.addFlashAttribute("message", "user does not exist");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		return new ModelAndView("redirect:connection");
		}
	}

}
