package com.paymybuddy.dto;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class UserRegistrationDto {
	 private String userName;
	 private String email;
	 private String mobileNumber;
	 private String passWord;
	private double balance;
	public UserRegistrationDto() {
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	 
}
