package com.revature;

import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {
		System.out.println("======================Users======================");
		User admin = new Admin(1, "adminUsername", "adminPassword", "Admin-Name", "Adminson", "admin@bank.com");
		User employee = new Employee(2, "employeeUsername", "employeePassword", "Employee-Name", "Employeeson", "employee@bank.com");
		User standard = new Standard(3, "standardUsername", "standardPassword", "Standard-Name", "Standardson", "standard@client.com");
		
		System.out.println(admin.getUsername() + " is a(n) " + admin.role);
		System.out.println(employee.getUsername() + " is a(n) " + employee.role);
		System.out.println(standard.getUsername() + " is a(n) " + standard.role);
		
		admin.setUsername("NewAdminUsername");
		System.out.println(admin.getUsername());
		
		
		System.out.println("======================Accounts======================");
		
		Account myChecking = new Checking(1, 842.24, "Open");
		Account mySavings = new Savings(2, 320578.25, "Closed");
		
		System.out.println("I have $" + myChecking.balance + " in my " + myChecking.accountType + " account.");
		System.out.println("I have $" + mySavings.balance + " in my " + mySavings.accountType + " account.");
		
		System.out.println(myChecking.withdraw(myChecking.accountID, 400.83));
		System.out.println(mySavings.withdraw(mySavings.accountID, 6375230.75));
		
		System.out.println(myChecking.deposit(myChecking.accountID, 38920.34));
		System.out.println(mySavings.deposit(mySavings.accountID, 34.23));
		
		System.out.println(myChecking.transfer(myChecking.accountID, mySavings.accountID, 64839.23));
	}

}
