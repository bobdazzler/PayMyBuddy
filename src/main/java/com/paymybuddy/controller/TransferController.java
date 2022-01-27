package com.paymybuddy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.Transfer;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.TransferRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.ConnectionServiceImpl;
@RestController
public class TransferController {
	Logger logger =  LoggerFactory.getLogger(TransferController.class);
	@Autowired
	TransferRepository transferRepository;
	@Autowired
	private ConnectionServiceImpl connectionServiceImpl;
	@Autowired
	UserRepository userRepository;
	User user = new User(); 
	@GetMapping("/transfer")
	public ModelAndView showTransfer(@ModelAttribute("transfer") Transfer transfer, @ModelAttribute("connection") Connection connection,
			Model model, HttpServletRequest request,BindingResult result,RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");	
		logger.info("logged in user"+ userId);
		List<Connection> connectionsEmailOfUsersAdded = new ArrayList<>();
		for(Connection connectedUser:connectionServiceImpl.listOfConnectedEmail()) {
			if(connectedUser.getUser_id()==userId) {
				connectionsEmailOfUsersAdded.add(connectedUser);
				model.addAttribute("connections", connectionsEmailOfUsersAdded);
			}
		}

		return new ModelAndView("transfer");
	}
	@Transactional
	@PostMapping("/makePayment")
	public ModelAndView sendMoney(@ModelAttribute("transfer") Transfer transfer,HttpServletRequest request,
			BindingResult result,RedirectAttributes redirectAttributes) {
		int userId = (int) request.getSession().getAttribute("userId");	
		Transfer transferDetails = new Transfer();
		User recieverDetails = connectionServiceImpl.checkingIfUserExist(transfer.getRecieverEmail());
		transferDetails.setAmount(transfer.getAmount());
		transferDetails.setRecieverId(recieverDetails.getId());
		transferDetails.setUserId(userId);
		transferRepository.save(transfer);
		double recieverTotal = transfer.getAmount()+ recieverDetails.getBalance();
		recieverDetails.setBalance(recieverTotal);
		userRepository.save(recieverDetails);
		for(User loggedInUser: userRepository.findAll() ) {	
			if(loggedInUser.getId()==userId) {
				double senderTotal =loggedInUser.getBalance()-transfer.getAmount();
				loggedInUser.setBalance(senderTotal);
			}
		}
		redirectAttributes.addFlashAttribute("message", "Transfer successful");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return new ModelAndView("redirect:/transfer");
	}
}
