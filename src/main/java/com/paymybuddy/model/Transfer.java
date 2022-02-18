package com.paymybuddy.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="transfer")
public class Transfer {
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
	@Transient
	private String description;
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getCharges() {
		return charges;
	}
	public int getUserId() {
		return userId;
	}
	public int getRecieverId() {
		return recieverId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}
	public Transfer(BigDecimal amount, String recieverEmail, BigDecimal charges, int userId, int recieverId) {
		this.amount = amount;
		this.recieverEmail = recieverEmail;
		this.charges = charges;
		this.userId = userId;
		this.recieverId = recieverId;
	}
	public Transfer() {
	}
}
