package com.revature.models;

public class Savings extends Account{

	public Savings() {
		super();
	}

	public Savings(int accountID, double balance, String status) {
		super(accountID, balance, status);
		this.accountType = "Savings";
	}
	
}
