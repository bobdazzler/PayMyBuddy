package com.paymybuddy.model;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import org.springframework.lang.NonNull;


@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@NonNull
	@Column(name = "user_name")
	private String userName;
	@NonNull 
	@Column(name = "email")
	private String email;
	@NonNull 
	@Column(name = "mobile_number")
	private String mobileNumber;
	@NonNull 
	@Column(name = "password")
	private String passWord;
	@NonNull 
	@Column(name="balance")
	private double balance;
	@OneToMany
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Connection> connections;
	public User() {	
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
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

	public List<Connection> getConnections() {
		return connections;
	}
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public User(String userName, String email, String mobileNumber, String passWord, double balance) {
		this.userName = userName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.passWord = passWord;
		this.balance = balance;
	}

}
