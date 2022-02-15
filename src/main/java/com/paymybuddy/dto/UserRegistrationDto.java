package com.paymybuddy.dto;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class UserRegistrationDto {
	 private String userName;
	 private String email;
	 private String mobileNumber;
	 private String passWord;
	private BigDecimal amount;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public UserRegistrationDto(String userName, String email, String mobileNumber, String passWord) {
		this.userName = userName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.passWord = passWord;
	}
	public UserRegistrationDto() {
	}
	
}
