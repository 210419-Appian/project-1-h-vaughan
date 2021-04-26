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
	
}
