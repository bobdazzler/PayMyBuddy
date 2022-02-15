package com.paymybuddy.controller;
import java.math.BigDecimal;
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
import com.paymybuddy.service.ConnectionServiceImpl;
import com.paymybuddy.service.TransactionServiceImpl;
import com.paymybuddy.service.TransferServiceImpl;
import com.paymybuddy.service.UserServiceImpl;
@RestController
public class TransferController {
	Logger logger =  LoggerFactory.getLogger(TransferController.class);
	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	@Autowired
	ConnectionRepository connectionRepository;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	ConnectionServiceImpl connectionServiceImpl;
	@Autowired
	TransferServiceImpl transferServiceImpl;

	@GetMapping("/transfer")
	public ModelAndView showTransfer(@ModelAttribute("transfer") Transfer transfer,Model model,
			HttpServletRequest request) {
		if(request.getSession().getAttribute("userId")!= null) {
			int userId = (int) request.getSession().getAttribute("userId");	
			List<Transaction> transactionsOfUser = new ArrayList<>();
			List<Transaction> transactions = transactionServiceImpl.findTransaction();
			for(Transaction transactionOfUser :transactions) {
				if(transactionOfUser.getUserId()==userId) {
					transactionsOfUser.add(transactionOfUser);
				}
			}
			model.addAttribute("transactions", transactionsOfUser);
			List<Connection> connectionsEmailOfUsersAdded = new ArrayList<>();
			List<Connection>connections = connectionServiceImpl.listOfConnectedEmail();
			for(Connection connectedUser : connections) {
				if(connectedUser.getUserId()==userId) {
					connectionsEmailOfUsersAdded.add(connectedUser);
				}
			}
			model.addAttribute("connections", connectionsEmailOfUsersAdded);
			return new ModelAndView("transfer");
		}else {
			return new ModelAndView("home");
		}
	}
	@Transactional
	@PostMapping("/makePayment")
	public ModelAndView sendMoney(@ModelAttribute("transfer") TransferDto transferDto,HttpServletRequest request,
			BindingResult result,RedirectAttributes redirectAttributes) {
		Transfer transfer = new Transfer();
		Transaction transaction = new  Transaction();
		int userId = (int) request.getSession().getAttribute("userId");	
		User loggedInUser = userServiceImpl.getUserById(userId);
		User recieverDetails = connectionServiceImpl.checkingIfUserExist(transferDto.getRecieverEmail());
		if(recieverDetails == null) {
			redirectAttributes.addFlashAttribute("message", "please add a connection");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");	
		}
		if(loggedInUser.getBalance().compareTo(transferDto.getAmount()) ==-1 ) {
			redirectAttributes.addFlashAttribute("message", "Please Add Money to your PayMyBuddy Balance");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");	
		}else {
			BigDecimal chargePercentage = new BigDecimal(0.005);
			BigDecimal charges = transferDto.getAmount().multiply(chargePercentage);
			BigDecimal senderTotal =(loggedInUser.getBalance().subtract(transferDto.getAmount())).subtract(charges);
			loggedInUser.setBalance(senderTotal);
			transfer.setAmount(transferDto.getAmount());
			transfer.setCharges(charges);
			transfer.setRecieverId(recieverDetails.getId());
			transfer.setUserId(userId);
			transfer.setRecieverEmail(recieverDetails.getEmail());
			transferServiceImpl.save(transfer);
			userServiceImpl.saveUpdatedUser(loggedInUser);
			BigDecimal recieverTotal = transferDto.getAmount().add(recieverDetails.getBalance());
			recieverDetails.setBalance(recieverTotal);
			userServiceImpl.saveUpdatedUser(recieverDetails);
			transaction.setAmount(transferDto.getAmount());
			transaction.setCharges(charges);
			transaction.setDescription(transferDto.getDescription());
			transaction.setRecieverEmail(recieverDetails.getEmail());
			transaction.setUserId(userId);
			transaction.setRecieverId(recieverDetails.getId());
			transactionServiceImpl.saveUserTransaction(transaction);
			redirectAttributes.addFlashAttribute("message", "Transfer successful");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		}
		return new ModelAndView("redirect:/transfer");
	}
}
