package com.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.Transfer;
import com.paymybuddy.repository.TransferRepository;
@Service
public class TransferServiceImpl implements TransferService{
	@Autowired
	TransferRepository transferRepository;

	@Override
	public Transfer save(Transfer transfer) {
		
		return transferRepository.save(transfer);
	}

}
