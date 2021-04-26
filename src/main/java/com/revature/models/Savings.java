package com.revature.models;

import com.revature.throwables.BalanceBelowZero;

public class Savings extends Account{

	public Savings() {
		super();
	}

	public Savings(int accountID, double balance, String status) {
		super(accountID, balance, status);
		this.accountType = "Savings";
	}
	
	@Override
	public String withdraw(int accountID, double amount) {
		try {
			if (amount > this.balance) {
				throw new BalanceBelowZero("You do not have enough funds in Savings Account #" + accountID);
			}
			else {
				this.balance = this.balance - amount;
			}
		} catch (BalanceBelowZero e) {
			e.printStackTrace();
		}
		return "$" + amount + " has been withdrawn from Savings Account #" + accountID + ". Your current balance is: $" + this.balance;
	}
	
	@Override
	public String deposit(int accountID, double amount) {
		
		this.balance = this.balance + amount;
		return "$" + amount + " has been deposited into Savings Account #" + accountID + ". Your current balance is: $" + this.balance;
		
	}
	
}
