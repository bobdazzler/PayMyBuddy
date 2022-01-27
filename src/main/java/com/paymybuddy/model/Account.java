package com.paymybuddy.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;
@DynamicUpdate
@Entity
@Table(name ="account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	@NonNull
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "bank_name")
	@NonNull
	private String bankName;
	@Column(name = "bank_account_number")
	private int bankAccountNumber;
	@NonNull
	@Column(name = "amount")
	private double amount;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Account() {
	}
	public Account(int user_id, String bankName, int bankAccountNumber, double amount) {
		this.user_id = user_id;
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.amount = amount;
	}
	
}
