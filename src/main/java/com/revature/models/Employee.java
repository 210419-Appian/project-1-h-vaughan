package com.revature.models;

public class Employee extends User{

	public  Employee() {
		super();
		this.roleID = 2;
		this.role = "Employee";
	}
	
	public Employee(String username, String password, String firstName, String lastName, String email) {
		super(username, password, firstName, lastName, email);
		this.roleID = 2;
		this.role = "Employee";
	}
	

	
}
