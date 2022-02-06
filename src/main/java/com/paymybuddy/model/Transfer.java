package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transfer")
public class Transfer {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	@Column(name ="amount")
	private double amount;
	@Column(name = "reciever_email")
	private String recieverEmail;
	@Column(name = "charges")
	private double charges;
	@Column(name = "user_id")
	private int userId;
	@Column(name ="reciever_id")
	private int recieverId;
	@Column(name="description")
	private String Description;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}