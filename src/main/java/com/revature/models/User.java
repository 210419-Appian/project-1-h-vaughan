package com.revature.models;

import com.revature.throwables.UserMisinput;
import com.revature.util.AllowedCharacters;

public class User {

	private int userID;
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	private String role; // not null

	public User() {
		super();
	}

	public User(String password, String firstName, String lastName, String email,
			String role) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public User(String username, String password, String firstName, String lastName, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		try {
			for(char c : username.toCharArray())
			{
				if (!AllowedCharacters.allowedCharactersUsername.contains(c)) {
					throw new UserMisinput("Invalid character: '" + c + "' Please try again~!");
				}
			}
			this.username = username;
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			for(char c : password.toCharArray())
			{
				if (!AllowedCharacters.allowedCharactersPassword.contains(c)) {
					throw new UserMisinput("Invalid character: '" + c + "' Please try again~!");
				}
			}
			this.password = password;
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		try {
			for(char c : firstName.toCharArray())
			{
				if (!AllowedCharacters.allowedCharactersFirst.contains(c)) {
					throw new UserMisinput("Invalid character: '" + c + "' Please try again~!");
				}
			}
			this.firstName = firstName;
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		try {
			for(char c : lastName.toCharArray())
			{
				if (!AllowedCharacters.allowedCharactersLast.contains(c)) {
					throw new UserMisinput("Invalid character: '" + c + "' Please try again~!");
				}
			}
			this.lastName = lastName;
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		try {
			for(char c : email.toCharArray())
			{
				if (!AllowedCharacters.allowedCharactersEmail.contains(c)) {
					throw new UserMisinput("Invalid character: '" + c + "' Please try again~!");
				}
			}
			this.email = email;
		}catch(UserMisinput e) {
			e.printStackTrace();
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}




}
