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
	private int userId;
	@Column(name = "connected_user_id")
	private int connectedUserId;
	@Column(name = "reciever_email")
	private String recieverEmail;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setConnectedUserId(int connectedUserId) {
		this.connectedUserId = connectedUserId;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	
	public Connection() {
	}
	public Connection(int userId, int connectedUserId, String recieverEmail) {
		this.userId = userId;
		this.connectedUserId = connectedUserId;
		this.recieverEmail = recieverEmail;
	}
	
}
