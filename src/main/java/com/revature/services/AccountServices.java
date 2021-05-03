package com.revature.services;

import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.throwables.BalanceBelowZero;
import com.revature.throwables.SecurityCheck;

public class AccountServices {
	
	private static AccountDAOImpl aDao = new AccountDAOImpl();
	
	public List<Account> findAll(User u) {
		try {
			if (u.getRole().equals("Admin") || u.getRole().equals("Employee")) {
				return aDao.findAll();
			} else {
				throw new SecurityCheck("You do not have permssion to view this information!"); 
			}
		}catch(SecurityCheck e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Account findByID(int id, User u) {
		try {
			if (u.getRole().equals("Admin") || u.getRole().equals("Employee")) {
				return aDao.findByID(id);
			} else {
				throw new SecurityCheck("You do not have permssion to view this information!"); 
			}
		}catch(SecurityCheck e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean addAccount(Account a) {
		//TODO: Make sure a is valid
		return aDao.addAccount(a);
	}
	
	public List<Account> findByUser(User u) {
		//TODO: Check User's permissions and make sure u is valid
		return aDao.findByUser(u);
	}
	
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
