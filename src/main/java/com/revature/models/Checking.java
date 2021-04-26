package com.revature.models;

public class Checking extends Account {

	public Checking() {
		super();
	}

	public Checking(int accountID, double balance, String status) {
		super(accountID, balance, status);
		this.accountType = "Checking";
	}
}
