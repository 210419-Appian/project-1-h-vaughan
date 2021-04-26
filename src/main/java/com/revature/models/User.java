package com.revature.models;

import java.util.HashMap;

public abstract class User {
	
	public int userID; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	public int roleID; // primary key
	public String role; // not null, unique
	
	public User() {
		super();
	}
	
	public User(String username, String password, String firstName, String lastName, String email) {
		super();
		
		int IDcount = 1; //In theory, this should make sure that no user has the same ID
		while (users.containsKey(IDcount) == true) {
			IDcount++;
		}
		this.userID = IDcount;
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		users.put(this.userID, this);
	}
	
	
	
	public void setUsername(String newUsername) {
		//All users must be able to update their username!
		
		this.username = newUsername; //Add some functionality to make sure not null and unique
	}
	
	public String getUsername() {
		//All users should be able to view their own information
		return this.username;
	}
	
	public String getUsername(int userID) {
		if (this.roleID == 1) {
			//User is an admin and can view any information
			return userID + "test";
		}
		else if (this.roleID == 2) {
			//User is an employee and can view any information
			return userID + "test";
		}
		else {
			//User is not an admin nor an employee, and should only be able to view their own information
			return "You do not have permission to view this information.";
		}
	}
	
	
	
	public void setPassword(String newPassword) {
		//All users must be able to update their password!
		
		this.password = newPassword; //Add some functionality to make sure not null
	}
	
	public String getPassword() {
		//All users should be able to view their own information
		return this.password;
	}
	
	public String getPassword(int userID) {
		if (this.roleID == 1) {
			//User is an admin and can view any information
			return userID + "test";
		}
		else if (this.roleID == 2) {
			//User is an employee and can view any information
			return userID + "test";
		}
		else {
			//User is not an admin nor an employee, and should only be able to view their own information
			return "You do not have permission to view this information.";
		}
	}
	
	
	
	public void setFirstName(String newFirstName) {
		//All users must be able to update their first name!
		
		this.firstName = newFirstName; //Add some functionality to make sure not null
	}
	
	public String getFirstName() {
		//All users should be able to view their own information
		return this.firstName;
	}
	
	public String getFirstName(int userID) {
		if (this.roleID == 1) {
			//User is an admin and can view any information
			return userID + "test";
		}
		else if (this.roleID == 2) {
			//User is an employee and can view any information
			return userID + "test";
		}
		else {
			//User is not an admin nor an employee, and should only be able to view their own information
			return "You do not have permission to view this information.";
		}
	}
	
	
	
	public void setLastName(String newLastName) {
		//All users must be able to update their last name!
		
		this.lastName = newLastName; //Add some functionality to make sure not null
	}
	
	public String getLastName() {
		//All users should be able to view their own information
		return this.lastName;
	}
	
	public String getLastName(int userID) {
		if (this.roleID == 1) {
			//User is an admin and can view any information
			return userID + "test";
		}
		else if (this.roleID == 2) {
			//User is an employee and can view any information
			return userID + "test";
		}
		else {
			//User is not an admin nor an employee, and should only be able to view their own information
			return "You do not have permission to view this information.";
		}
	}
	
	
	
	public void setEmail(String newEmail) {
		//All users must be able to update their email!
		
		this.email = newEmail; //Add some functionality to make sure not null
	}
	
	public String getEmail() {
		//All users should be able to view their own information
		return this.email;
	}
	
	public String getEmail(int userID) {
		if (this.roleID == 1) {
			//User is an admin and can view any information
			return userID + "test";
		}
		else if (this.roleID == 2) {
			//User is an employee and can view any information
			return userID + "test";
		}
		else {
			//User is not an admin nor an employee, and should only be able to view their own information
			return "You do not have permission to view this information.";
		}
	}
	
	
}
