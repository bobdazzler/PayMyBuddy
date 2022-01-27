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
	private int amount;
	@Column(name = "reciever_email")
	private String recieverEmail;
	@Column(name = "charges")
	private int charges;
	@Column(name = "user_id")
	private int userId;
	@Column(name ="reciever_id")
	private int recieverId;
	@Column(name="description")
	private String Description;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}	
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public int getCharges() {
		return charges;
	}
	public void setCharges(int charges) {
		this.charges = charges;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
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
}
