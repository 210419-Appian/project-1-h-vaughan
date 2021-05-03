package com.revature;

import java.util.List;

import com.revature.daos.AccountDAOImpl;
import com.revature.daos.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

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
		

		
//		Account myAccount = new Account();
//		
//		System.out.println(myAccount.getBalance());
//		myAccount.setBalance(20.34);
//		System.out.println(myAccount.getBalance());
//		aServices.withdraw(myAccount, 8.23);
//		System.out.println(myAccount.getBalance());
//		aServices.withdraw(myAccount, 12.12);
//		System.out.println(myAccount.getBalance());
//		
//		Account myAccount2 = new Account();
//		
//		myAccount2.setBalance(10.00);
//		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
//		aServices.transfer(myAccount, myAccount2, 9.54);
//		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
//		aServices.transfer(myAccount, myAccount2, 9.54);
//		System.out.println("Acc 1: " + myAccount.getBalance() + "! Acc 2: " + myAccount2.getBalance() + "!");
		
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
		
		System.out.println("================================AccountServices================================");
		
		AccountServices aServices = new AccountServices();
				
		System.out.println(aServices.withdraw(aDao.findByID(6), 9000));	//should be true	
		System.out.println(aServices.withdraw(aDao.findByID(3), 25000)); //should be false
		
		System.out.println(aServices.deposit(aDao.findByID(4), 8909.21)); //should be true
		
		System.out.println(aServices.transfer(aDao.findByID(2), aDao.findByID(5), 0.22)); //should be true
		System.out.println(aServices.transfer(aDao.findByID(5), aDao.findByID(2), 0.23)); //should be false
		
		
		System.out.println("================================UserServices================================");
		
		UserServices uServices = new UserServices();
		
		User anotherUser = new User("AnotherUsername", "AnotherPassword", "Anna", "There", "another@account.com", "Standard");
		
		uDao.addUser(anotherUser);
		
		User fakeAdmin = new User("fakeUsername", "fakePassword", "Not", "Real", "notarealemail@adress.com", "Admin");
		
		uServices.registerAccount(20.00, "Checking", anotherUser);
		
		User loggedInUser = uServices.findByUsername("adminUsername", fakeAdmin);
		System.out.println(loggedInUser.getRole());
		
		System.out.println(uServices.findAll(uServices.findByUsername("adminUsername", fakeAdmin))); //should return the list
		System.out.println(uServices.findAll(uServices.findByUsername("AnotherUsername", fakeAdmin))); //should throw a SecurityCheck exception
		
		System.out.println(aServices.findAll(fakeAdmin));
		System.out.println(aServices.findByID(2, fakeAdmin));
		
	}

}
