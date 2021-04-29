package com.revature.services;

import com.revature.models.Account;
import com.revature.throwables.BalanceBelowZero;

public class AccountServices {
	
	public Boolean withdraw(Account account, double amount) {
		try {
			if(account.getBalance() < amount) {
				throw new BalanceBelowZero("You do not have enough money in this account for this transaction! Please try again.");
			}else {
				//TODO Update the balance in the database
				account.setBalance(account.getBalance() - amount);
				return true;
			}
		}catch(BalanceBelowZero e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean deposit(Account account, double amount) {
		//TODO Update the balance in the database
		account.setBalance(account.getBalance() + amount);
		return true;
	}
	
	public Boolean transfer(Account sourceAccount, Account targetAccount, double amount) {
		try {
			if(sourceAccount.getBalance() < amount) {
				throw new BalanceBelowZero("You do not have enough money in this account for this transaction! Please try again.");
			}else {
				//TODO Update the balance in the database
				sourceAccount.setBalance(sourceAccount.getBalance() - amount);
				targetAccount.setBalance(targetAccount.getBalance() + amount);
				return true;
			}
		}catch(BalanceBelowZero e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public String withdraw(int accountID, double amount) {
//		return null;
//	}
//	
//	public String deposit(int accountID, double amount) {
//		return null;
//	}
//	
//	public String transfer(int sourceAccountID, int targetAccountID, double amount) {
//		try {
//			if (amount > this.balance) {
//				throw new BalanceBelowZero("You do not have enough funds in " + accounts.get(sourceAccountID).accountType + " Account #" + accountID + " to transfer. Please try again.");
//			}
//			else {
//				this.balance = this.balance - amount;
//				accounts.get(targetAccountID).balance = accounts.get(targetAccountID).balance + amount;
//				
//				return "$" + amount + " has been transferred from " + accounts.get(sourceAccountID).accountType + " Account #" + sourceAccountID + " to " + accounts.get(targetAccountID).accountType + " Account #" + targetAccountID;
//			}
//		} catch (BalanceBelowZero e) {
//			e.printStackTrace();
//		}
//		return "An error has ocurred. Please try again.";
//	}
}
