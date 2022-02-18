package com.paymybuddy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="transaction")
public class Transaction {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	@Column(name ="amount")
	private BigDecimal amount;
	@Column(name = "reciever_email")
	private String recieverEmail;
	@Column(name = "charges")
	private BigDecimal charges;
	@Column(name = "user_id")
	private int userId;
	@Column(name ="reciever_id")
	private int recieverId;
	@Column(name="description")
	private String Description;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Transaction(BigDecimal amount, String recieverEmail, int userId, String description) {
		this.amount = amount;
		this.recieverEmail = recieverEmail;
		this.userId = userId;
		Description = description;
	}
	public Transaction() {
	}
	public Transaction(BigDecimal amount, String recieverEmail, BigDecimal charges, int userId, int recieverId,
			String description) {
		this.amount = amount;
		this.recieverEmail = recieverEmail;
		this.charges = charges;
		this.userId = userId;
		this.recieverId = recieverId;
		Description = description;
	}
	
}
