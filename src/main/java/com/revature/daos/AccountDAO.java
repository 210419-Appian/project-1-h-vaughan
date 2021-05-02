package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDAO {

	List<Account> findAll();
	
	Account findByID(int id);
	
	boolean addAccount(Account a);
	
	List<Account> findByUser(User u);
	
	boolean withdraw(Account a, double amount);
	
	boolean deposit (Account a, double amount);
	
	boolean transfer(Account sourceAccount, Account targetAccount, double amount);
	
}
