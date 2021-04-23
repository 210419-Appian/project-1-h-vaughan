package com.revature.models;

public class Standard extends User{

	public  Standard() {
		super();
		this.roleID = 3;
		this.role = "Standard User";
	}
	
	public Standard(int userID, String username, String password, String firstName, String lastName, String email) {
		super(userID, username, password, firstName, lastName, email);
		this.roleID = 3;
		this.role = "Standard User";
	}
	
	
	
}
