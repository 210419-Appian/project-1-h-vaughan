package com.revature.models;

import java.util.HashMap;

import com.revature.throwables.BalanceBelowZero;

public abstract class Account {

	public int accountID; // primary key
	public static int nextID = 1;
	public double balance; // not null
	public String status;
	public String accountType;
	public static HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
	
	Account() {
		super();
	}
	
	Account(double balance, String status) {
		this.accountID = nextID;
		nextID += 1;
		this.balance = balance;
		this.status = status;
		accounts.put(accountID, this);
	}
	
	public abstract String withdraw(int accountID, double amount); 
	
	public abstract String deposit(int accountID, double amount);
	
	public String transfer(int sourceAccountID, int targetAccountID, double amount) {
		try {
			if (amount > this.balance) {
				throw new BalanceBelowZero("You do not have enough funds in Account #" + accountID + " to transfer. Please try again.");
			}
			else {
				this.balance = this.balance - amount;
				accounts.get(targetAccountID).balance = accounts.get(targetAccountID).balance + amount;
				
				return "$" + amount + " has been transferred from Account #" + sourceAccountID + " to Account #" + targetAccountID;
			}
		} catch (BalanceBelowZero e) {
			e.printStackTrace();
		}
		return "An error has ocurred. Please try again.";
	}
}
