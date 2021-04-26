package com.revature.models;

public class Admin extends User {

	public  Admin() {
		super();
		this.roleID = 1;
		this.role = "Admin";
	}
	
	public Admin(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email);
		this.roleID = 1;
		this.role = "Admin";
	}
	

	
}
