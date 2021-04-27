package com.revature.models;

import java.util.HashMap;

import com.revature.throwables.SecurityCheck;
import com.revature.throwables.UserMisinput;

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
		
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		
		users.put(this.userID, this);
	}
	
	
	
	public void setUsername(String newUsername) {
		//All users must be able to update their username!
		try {
			boolean isValid = true;
			for(char letter : newUsername.toCharArray()) {
				if(!AllowedCharacters.allowedCharactersUsername.contains(letter) || newUsername == null) {
					isValid = false;
					throw new UserMisinput("Please enter a valid username");
				}
			}
			if(isValid) {
				this.username = newUsername;
			}
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
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
		try {
			boolean isValid = true;
			for(char letter : newPassword.toCharArray()) {
				if(!AllowedCharacters.allowedCharactersPassword.contains(letter) || newPassword == null) {
					isValid = false;
					throw new UserMisinput("Please enter a valid password");
				}
			}
			if(isValid) {
				this.password = newPassword;
			}
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
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
		try {
			boolean isValid = true;
			for(char letter : newFirstName.toCharArray()) {
				if(!AllowedCharacters.allowedCharactersFirst.contains(letter) || newFirstName == null) {
					isValid = false;
					throw new UserMisinput("Please enter a valid name");
				}
			}
			if(isValid) {
				this.firstName = newFirstName;
			}
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
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
		try {
			boolean isValid = true;
			for(char letter : newLastName.toCharArray()) {
				if(!AllowedCharacters.allowedCharactersLast.contains(letter) || newLastName == null) {
					isValid = false;
					throw new UserMisinput("Please enter a valid name");
				}
			}
			if(isValid) {
				this.lastName = newLastName;
			}
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
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
		try {
			boolean isValid = true;
			for(char letter : newEmail.toCharArray()) {
				if(!AllowedCharacters.allowedCharactersEmail.contains(letter) || newEmail == null) {
					isValid = false;
					throw new UserMisinput("Please enter a valid email");
				}
			}
			if(isValid) {
				this.email = newEmail;
			}
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
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
