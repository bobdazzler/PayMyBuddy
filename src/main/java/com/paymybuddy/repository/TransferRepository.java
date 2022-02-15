package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer ,Integer> {
	
}
