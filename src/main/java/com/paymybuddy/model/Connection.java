package com.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="connection")
public class Connection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "connected_user_id")
	private int connected_user_id;
	@Column(name = "reciever_email")
	private String recieverEmail;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getConnected_user_id() {
		return connected_user_id;
	}
	public void setConnected_user_id(int connected_user_id) {
		this.connected_user_id = connected_user_id;
	}
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
}
