package com.revature.services;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.throwables.BalanceBelowZero;

public class AccountServices {
	
	private static AccountDAOImpl aDao = new AccountDAOImpl();
	
	public boolean withdraw(Account account, double amount) {
		try {
			if(account.getBalance() < amount) {
				throw new BalanceBelowZero("You do not have enough money in this account for this transaction! Please try again.");
			}else {
				return aDao.withdraw(account, amount);
			}
		}catch(BalanceBelowZero e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deposit(Account account, double amount) {
		return aDao.deposit(account, amount);
	}
	
	public boolean transfer(Account sourceAccount, Account targetAccount, double amount) {
		try {
			if(sourceAccount.getBalance() < amount) {
				throw new BalanceBelowZero("You do not have enough money in this account for this transaction! Please try again.");
			}else {
				return aDao.transfer(sourceAccount, targetAccount, amount);
			}
		}catch(BalanceBelowZero e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
