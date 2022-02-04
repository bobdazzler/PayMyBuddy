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

import com.paymybuddy.dto.TransferDto;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.Transfer;
import com.paymybuddy.model.User;
import com.paymybuddy.repository.ConnectionRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.repository.TransferRepository;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.ConnectionServiceImpl;
@RestController
public class TransferController {
	Logger logger =  LoggerFactory.getLogger(TransferController.class);
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	TransferRepository transferRepository;
	@Autowired
	private ConnectionRepository connectionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ConnectionServiceImpl connectionServiceImpl;
	User user = new User(); 
	@GetMapping("/transfer")
	public ModelAndView showTransfer(@ModelAttribute("transfer") Transfer transfer, @ModelAttribute("connection") Connection connection,
			Model model, HttpServletRequest request) {
		int userId = (int) request.getSession().getAttribute("userId");	
		List<Transaction> transactionsOfUser = new ArrayList<>();
		List<Transaction> transactions = transactionRepository.findAll();
		for(Transaction transactionOfUser :transactions) {
			if(transactionOfUser.getUserId()==userId) {
				transactionsOfUser.add(transactionOfUser);
			}
		}
		model.addAttribute("transactions", transactionsOfUser);
		List<Connection> connectionsEmailOfUsersAdded = new ArrayList<>();
		List<Connection>connections = connectionRepository.findAll();
		for(Connection connectedUser : connections) {
			if(connectedUser.getUserId()==userId) {
				connectionsEmailOfUsersAdded.add(connectedUser);
			}
		}
		model.addAttribute("connections", connectionsEmailOfUsersAdded);
		return new ModelAndView("transfer");
	}
	@Transactional
	@PostMapping("/makePayment")
	public ModelAndView sendMoney(@ModelAttribute("transfer") TransferDto transferDto,HttpServletRequest request,
			BindingResult result,RedirectAttributes redirectAttributes) {
		Transfer transfer = new Transfer();
		Transaction transaction = new  Transaction();
		int userId = (int) request.getSession().getAttribute("userId");	
		User loggedInUser = userRepository.getUserById(userId);

		User recieverDetails = connectionServiceImpl.checkingIfUserExist(transferDto.getRecieverEmail());
		if(recieverDetails == null) {
			redirectAttributes.addFlashAttribute("message", "please add a connection");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return new ModelAndView("redirect:/transfer");	
		}
		if(loggedInUser.getBalance() <= 0.0 ) {
			redirectAttributes.addFlashAttribute("message", "Please Add Money to your PayMyBuddy Balance");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return new ModelAndView("redirect:/transfer");	
		}
		double charges = transferDto.getAmount()*0.005;
		double senderTotal =(loggedInUser.getBalance()-transferDto.getAmount())-charges;
		loggedInUser.setBalance(senderTotal);
		transfer.setAmount(transferDto.getAmount());
		transfer.setCharges(charges);
		transfer.setRecieverId(recieverDetails.getId());
		transfer.setUserId(userId);
		transfer.setRecieverEmail(recieverDetails.getEmail());
		transferRepository.save(transfer);
		userRepository.save(loggedInUser);
		double recieverTotal = transferDto.getAmount()+ recieverDetails.getBalance();
		recieverDetails.setBalance(recieverTotal);
		userRepository.save(recieverDetails);
		transaction.setAmount(transferDto.getAmount());
		transaction.setCharges(charges);
		transaction.setDescription(transferDto.getDescription());
		transaction.setRecieverEmail(recieverDetails.getEmail());
		transaction.setUserId(userId);
		transaction.setRecieverId(recieverDetails.getId());
		transactionRepository.save(transaction);
		redirectAttributes.addFlashAttribute("message", "Transfer successful");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return new ModelAndView("redirect:/transfer");
	}
}
