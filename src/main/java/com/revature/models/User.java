package com.revature.models;

import java.util.HashMap;

import com.revature.throwables.SecurityCheck;

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
	
	public String getUsername(int targetUserID) {
		try {
			if(this.roleID == 1 || this.roleID == 2) {
				return users.get(targetUserID).username;
			}
			else {
				throw new SecurityCheck("You do not have permission to view this information.");
			}
		}
		catch(SecurityCheck e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	public void setPassword(String newPassword) {
		//All users must be able to update their password!
		
		this.password = newPassword; //Add some functionality to make sure not null
	}
	
	public String getPassword() {
		//All users should be able to view their own information
		return this.password;
	}
	
	public String getPassword(int targetUserID) {
		try {
			if(this.roleID == 1 || this.roleID == 2) {
				return users.get(targetUserID).password;
			}
			else {
				throw new SecurityCheck("You do not have permission to view this information.");
			}
		}
		catch(SecurityCheck e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void setFirstName(String newFirstName) {
		//All users must be able to update their first name!
		
		this.firstName = newFirstName; //Add some functionality to make sure not null
	}
	
	public String getFirstName() {
		//All users should be able to view their own information
		return this.firstName;
	}
	
	public String getFirstName(int targetUserID) {
		try {
			if(this.roleID == 1 || this.roleID == 2) {
				return users.get(targetUserID).firstName;
			}
			else {
				throw new SecurityCheck("You do not have permission to view this information.");
			}
		}
		catch(SecurityCheck e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void setLastName(String newLastName) {
		//All users must be able to update their last name!
		
		this.lastName = newLastName; //Add some functionality to make sure not null
	}
	
	public String getLastName() {
		//All users should be able to view their own information
		return this.lastName;
	}
	
	public String getLastName(int targetUserID) {
		try {
			if(this.roleID == 1 || this.roleID == 2) {
				return users.get(targetUserID).lastName;
			}
			else {
				throw new SecurityCheck("You do not have permission to view this information.");
			}
		}
		catch(SecurityCheck e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void setEmail(String newEmail) {
		//All users must be able to update their email!
		
		this.email = newEmail; //Add some functionality to make sure not null
	}
	
	public String getEmail() {
		//All users should be able to view their own information
		return this.email;
	}
	
	public String getEmail(int targetUserID) {
		try {
			if(this.roleID == 1 || this.roleID == 2) {
				return users.get(targetUserID).email;
			}
			else {
				throw new SecurityCheck("You do not have permission to view this information.");
			}
		}
		catch(SecurityCheck e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getAllInformation(int targetUserID) {
		String targetUsername = getUsername(targetUserID);
		String targetPassword = getPassword(targetUserID);
		String targetFirstName = getFirstName(targetUserID);
		String targetLastName = getLastName(targetUserID);
		String targetEmail = getEmail(targetUserID);
		return "User #" + targetUserID + "\n" + "Username: " + targetUsername + "\n" + "Password: " + targetPassword + "\n" + "Name: " + targetFirstName + " " + targetLastName + "\n" + "Email: " + targetEmail;
	}
	
}
