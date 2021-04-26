package com.revature.models;

public abstract class Account {

	public int accountID; // primary key
	public double balance; // not null
	public String status;
	public String accountType;
	
	Account() {
		super();
	}
	
	Account(int accountID, double balance, String status) {
		this.accountID = accountID;
		this.balance = balance;
		this.status = status;
	}
	
	public abstract String withdraw(int accountID, double amount); 
	
	public abstract String deposit(int accountID, double amount);
	
	public String transfer(int sourceAccountID, int targetAccountID, double amount) {
		//Need to add actual money transferring between Accounts
		return "$" + amount + " has been transferred from Account #" + sourceAccountID + " to Account #" + targetAccountID;
	}
}
