package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.throwables.BalanceBelowZero;
import com.revature.throwables.SecurityCheck;

public class AccountServices {
	
	private static AccountDAOImpl aDao = new AccountDAOImpl();
	
	public List<Account> findAll() {
		return aDao.findAll();
	}
	
	public Account findByID(int id) {
		return aDao.findByID(id);
	}
	
	public boolean addAccount(Account a, int owner) {
		a.setStatus("Pending");
		a.setOwner(owner);
		if(a.getAccountType().equals("Checking")) {
			a.setInterestRate(0);
		}
		return aDao.addAccount(a);
	}
	
	public List<Account> findByUser(User u) {
		//TODO: Check User's permissions and make sure u is valid
		return aDao.findByUser(u);
	}
	
	public List<Account> findByStatus(String status) {
		return aDao.findByStatus(status);
	}
	
	public boolean updateAccount(Account a, int id) {
		Account a2 = findByID(id);
		if(a2 != null) {
			if(a2.getAccountID() == id) {
				return aDao.updateAccount(a, id);				
			}else {
				return false;
			}
		}else {
			return aDao.updateAccount(a, id);
		}

	}
	
	public boolean withdraw(Account account, double amount) throws BalanceBelowZero{
		if(account.getBalance() < amount) {
			throw new BalanceBelowZero("You do not have enough money in this account for this transaction! Please try again.");
		}else {
			return aDao.withdraw(account, amount);
		}
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

	public boolean deleteAccount(Account targetAccount) {
		return aDao.deleteAccount(targetAccount);
	}
	
	public void accrueInterest(int months) {
		List<Account> accounts = findAll();
		while (months > 0) {
			for(Account a : accounts) {
				if(a.getStatus().equals("Open") && a.getAccountType().equals("Savings")) {
					double interest = a.getBalance()*a.getInterestRate();
					a.setBalance(a.getBalance() + interest);
					updateAccount(a, a.getAccountID());
				}
			}
			months--;
		}
	}
	
}
