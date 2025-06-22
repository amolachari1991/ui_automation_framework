package com.ui.pojo;

public class User {
	
	private String emailAddress;
	private String Password;
	
	public User(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		Password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "User [emailAddress=" + emailAddress + ", Password=" + Password + "]";
	}

	

}
