package com.revature;

import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {
		User admin = new Admin(1, "adminUsername", "adminPassword", "Admin-Name", "Adminson", "admin@bank.com");
		User employee = new Employee(2, "employeeUsername", "employeePassword", "Employee-Name", "Employeeson", "employee@bank.com");
		User standard = new Standard(3, "standardUsername", "standardPassword", "Standard-Name", "Standardson", "standard@client.com");
		
		System.out.println(admin.username + " is a(n) " + admin.role);
		System.out.println(employee.username + " is a(n) " + employee.role);
		System.out.println(standard.username + " is a(n) " + standard.role);

	}

}
