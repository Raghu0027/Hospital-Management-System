package com.entity;

public class User 
{
	private int id;
	private String fullName;
	private String  email;
	private String password;


	public User() {}

	public User(String fullName, String email, String password) 
	{
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	// id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// full name
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	//email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}