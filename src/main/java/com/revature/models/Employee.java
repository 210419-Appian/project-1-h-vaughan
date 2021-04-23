package com.revature.models;

public class Employee extends User{

	public  Employee() {
		super();
		this.roleID = 2;
		this.role = "Employee";
	}
	
	public Employee(int userID, String username, String password, String firstName, String lastName, String email) {
		super(userID, username, password, firstName, lastName, email);
		this.roleID = 2;
		this.role = "Employee";
	}
	
}
