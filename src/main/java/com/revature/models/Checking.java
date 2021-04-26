package com.revature.models;

import com.revature.throwables.BalanceBelowZero;

public class Checking extends Account {


	public Checking() {
		super();
	}

	public Checking(double balance, String status) {
		super(balance, status);
		this.accountType = "Checking";
	}
	
	@Override
	public String withdraw(int accountID, double amount) {
		try {
			if (amount > this.balance) {
				throw new BalanceBelowZero("You do not have enough funds in Checking Account #" + accountID);
			}
			else {
				this.balance = this.balance - amount;
			}
		} catch (BalanceBelowZero e) {
			e.printStackTrace();
		}
		return "$" + amount + " has been withdrawn from Checking Account #" + accountID + ". Your current balance is: $" + this.balance;
	}

	@Override
	public String deposit(int accountID, double amount) {
		
		this.balance = this.balance + amount;
		return "$" + amount + " has been deposited into Checking Account #" + accountID + ". Your current balance is: $" + this.balance;
		
	}
	
}
