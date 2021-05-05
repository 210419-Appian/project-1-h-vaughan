package com.revature.models;

public class UserDTO {

	public String username;
	public String password;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
