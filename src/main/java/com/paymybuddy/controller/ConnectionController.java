package com.paymybuddy.controller;
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
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ConnectionRepository;
import com.paymybuddy.service.ConnectionServiceImpl;
@RestController
public class ConnectionController {
	@Autowired 
	ConnectionRepository connectionRepository;
	@Autowired
	ConnectionServiceImpl connectionServiceImp;
	@GetMapping("/connection")
	public ModelAndView getUser(@ModelAttribute("connection") Connection connection) {
		return new ModelAndView("connection");
	}
	@PostMapping("/saveConnection")
	public ModelAndView saveConnection(@ModelAttribute("connection") Connection connection,HttpServletRequest request,BindingResult result,
			RedirectAttributes redirectAttributes, Model model){
		int userId = (int) request.getSession().getAttribute("userId");
		
	User user =	connectionServiceImp.checkingIfUserExist(connection.getRecieverEmail());
	if(user!=null) {
	connection.setConnected_user_id(user.getId());
	connection.setUser_id(userId);
	connection.setRecieverEmail(user.getEmail());
		connectionRepository.save(connection);
		return new ModelAndView("redirect:transfer");
	}
	result.rejectValue("recieverEmail", "error.recieverEmail", "You cannot use this username!");
	  redirectAttributes.addFlashAttribute("message", "user does not exist");
    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    return new ModelAndView("redirect:connection");
	}
	
}
