package com.revature.models;

public abstract class User {
	
	public int userID; // primary key
	public String username; // not null, unique
	public String password; // not null
	public String firstName; // not null
	public String lastName; // not null
	public String email; // not null
	
	public int roleID; // primary key
	public String role; // not null, unique
	
	public User() {
		super();
	}
	
	public User(int userID, String username, String password, String firstName, String lastName, String email) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	
}
