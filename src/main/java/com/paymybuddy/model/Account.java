package com.paymybuddy.model;
import java.math.BigDecimal;

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
	@Column(name = "userId")
	private int userId;
	@Column(name = "bank_name")
	@NonNull
	private String bankName;
	@Column(name = "bank_account_number")
	private int bankAccountNumber;
	@NonNull
	@Column(name = "amount")
	private BigDecimal amount;
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBankName() {
		return bankName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Account(int id, int userId, String bankName, int bankAccountNumber, BigDecimal amount) {
		this.id = id;
		this.userId = userId;
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.amount = amount;
	}
	public Account(int userId, String bankName, int bankAccountNumber, BigDecimal amount) {
		this.userId = userId;
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.amount = amount;
	}
	public Account(String bankName, int bankAccountNumber, BigDecimal amount) {
		this.bankName = bankName;
		this.bankAccountNumber = bankAccountNumber;
		this.amount = amount;
	}
	public Account() {
	}
	
}
