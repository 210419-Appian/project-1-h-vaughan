package com.revature;

import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;

public class Driver {

	public static void main(String[] args) {
		
		User myUser = new User();
		
		System.out.println("================================Getters&Setters================================");
		
		System.out.println(myUser.getUsername());
		myUser.setUsername("aValidUsername");
		System.out.println(myUser.getUsername());
		myUser.setUsername("NOT21314$215^& (@ FNVIALID EUSNFEMA0921-4398");
		System.out.println(myUser.getUsername());
		
		System.out.println(myUser.getPassword());
		myUser.setPassword("validPa$$Q0rd");
		System.out.println(myUser.getPassword());
		myUser.setPassword("NOT (@ FNVIALID EUSNFEMA0921-4398");
		System.out.println(myUser.getPassword());
		
		System.out.println("================================AccountServices================================");
		
		AccountServices aServices = new AccountServices();
		
		Account myAccount = new Account();
		
		System.out.println(myAccount.getBalance());
		myAccount.setBalance(20.34);
		System.out.println(myAccount.getBalance());
		aServices.withdraw(myAccount, 8.23);
		System.out.println(myAccount.getBalance());
		aServices.withdraw(myAccount, 12.12);
		System.out.println(myAccount.getBalance());
		
		Account myAccount2 = new Account();
		
		myAccount2.setBalance(10.00);
		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
		aServices.transfer(myAccount, myAccount2, 9.54);
		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
		aServices.transfer(myAccount, myAccount2, 9.54);
		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
		
		System.out.println("================================DatabaseConnectivity================================");
		
		UserDAOImpl uDao = new UserDAOImpl();
		
		List<User> list = uDao.findAll();
		
		for(User u : list) {
			System.out.println(u);
		}
		
		System.out.println(uDao.findByUsername("standardUsername"));
		System.out.println(uDao.findByUsername("adminUsername"));
		System.out.println(uDao.findByUsername("employeeUsername"));
		
		User newJavaUser = new User("JavaUsername", "JavaPassword", "J.", "Ava", "java@oracle.com", "Standard");
		
		uDao.addUser(newJavaUser);
		
		System.out.println(uDao.findByUsername("JavaUsername"));
		
		
		AccountDAOImpl aDao = new AccountDAOImpl();
		
		List<Account> list2 = aDao.findAll();
		
		for(Account a : list2) {
			System.out.println(a);
		}
		
		System.out.println(aDao.findByID(3));
		
		Account newAccount = new Account(9823.25, "Open", "Checking", newJavaUser);
		
		aDao.addAccount(newAccount);
		
		List<Account> list3 = aDao.findByUser(uDao.findByUsername("standardUsername"));
		
		for(Account a : list3) {
			System.out.println(a);
		}
		
	}

}
