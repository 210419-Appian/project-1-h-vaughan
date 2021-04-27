package com.revature;

import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {
		System.out.println("======================Users======================");
		
		User admin = new Admin("adminUsername", "adminPassword", "Admin-Name", "Adminson", "admin@bank.com");
		User employee = new Employee("employeeUsername", "employeePassword", "Employee-Name", "Employeeson", "employee@bank.com");
		User standard = new Standard("standardUsername", "standardPassword", "Standard-Name", "Standardson", "standard@client.com");
		
		System.out.println(admin.getUsername() + " is a(n) " + admin.role);
		System.out.println(employee.getUsername() + " is a(n) " + employee.role);
		System.out.println(standard.getUsername() + " is a(n) " + standard.role); //Getting a user's own username
		
		admin.setUsername("NewAdminUsername");
		System.out.println(admin.getUsername());
				
		
		System.out.println("======================Accounts======================");
		
		Account myChecking = new Checking(842.24, "Open");
		Account mySavings = new Savings(320578.25, "Closed");
		
		System.out.println("I have $" + myChecking.balance + " in my " + myChecking.accountType + " account.");
		System.out.println("I have $" + mySavings.balance + " in my " + mySavings.accountType + " account.");
		
		System.out.println(myChecking.withdraw(myChecking.accountID, 400.83));
		System.out.println(mySavings.withdraw(mySavings.accountID, 6375230.75));
		
		System.out.println(myChecking.deposit(myChecking.accountID, 38920.34));
		System.out.println(mySavings.deposit(mySavings.accountID, 34.23));
		
		System.out.println(myChecking.transfer(myChecking.accountID, mySavings.accountID, 64839.23));
		System.out.println(myChecking.balance);
		System.out.println(myChecking.transfer(myChecking.accountID, mySavings.accountID, 39.23));
		System.out.println(myChecking.balance);
		
		
		System.out.println("======================Collections======================");
		
		System.out.println(Account.accounts);
		System.out.println(User.users);
		
		System.out.println(Account.accounts.get(myChecking.accountID).balance);
		System.out.println(Account.accounts.get(mySavings.accountID).balance);
		myChecking.transfer(myChecking.accountID, mySavings.accountID, 30000);
		System.out.println(Account.accounts.get(myChecking.accountID).balance);
		System.out.println(Account.accounts.get(mySavings.accountID).balance);
		
		
		System.out.println("Account #2 is a(n) " + User.users.get(2).role);
		
		
		System.out.println("======================SecurityCheck======================");
		
		System.out.println("Admin access's User #3's username: " + admin.getUsername(3)); //Getting a particular user's username
		System.out.println("Employee access's User #3's username: " + employee.getUsername(3)); //Getting a particular user's username
		System.out.println("Standard user access's User #3's username: " + standard.getUsername(3)); //Getting a particular user's username
		
		System.out.println(admin.getAllInformation(3));
		System.out.println(employee.getAllInformation(1));
		System.out.println(standard.getAllInformation(3));
		
		System.out.println("======================AllowedCharacters======================");
		
		System.out.println("Username: " + standard.getUsername());
		standard.setUsername("This-Is-A-Valid_UsErN@m3");
		System.out.println("Username: " + standard.getUsername());
		standard.setUsername("t#is !S N0T==@= v@l1D username");
		System.out.println("Username: " + standard.getUsername());
		
		System.out.println("Password: " + standard.getPassword());
		standard.setPassword("Valid");
		System.out.println("Password: " + standard.getPassword());
		standard.setPassword("1nvalid pword");
		System.out.println("Password: " + standard.getPassword());
		
		System.out.println("First Name: " + standard.getFirstName());
		standard.setFirstName("Valid");
		System.out.println("First Name: " + standard.getFirstName());
		standard.setFirstName("1nvalid");
		System.out.println("First Name: " + standard.getFirstName());
		
		System.out.println("Last Name: " + standard.getLastName());
		standard.setLastName("Valid");
		System.out.println("Last Name: " + standard.getLastName());
		standard.setLastName("1nvalid");
		System.out.println("Last Name: " + standard.getLastName());
		
		System.out.println("Email: " + standard.getEmail());
		standard.setEmail("Valid@djsifo.com");
		System.out.println("Email: " + standard.getEmail());
		standard.setEmail("1nvalid at dkjfsak dot com");
		System.out.println("Email: " + standard.getEmail());
		
	}

}
