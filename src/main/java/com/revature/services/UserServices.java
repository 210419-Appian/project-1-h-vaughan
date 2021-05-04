package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.throwables.SecurityCheck;

public class UserServices {
	private static UserDAOImpl uDao = new UserDAOImpl();
		
	public List<User> findAll(User u) {
		try {
			if (u.getRole().equals("Admin") || u.getRole().equals("Employee")) {
				return uDao.findAll();
			} else {
				throw new SecurityCheck("You do not have permssion to view this information!"); 
			}
		}catch(SecurityCheck e) {
			e.printStackTrace();
		}
		List<User> empty = new ArrayList<User>();
		return empty;
	}
	
	public User findByUsername(String username, User u) {
		try {
			if (u.getRole().equals("Admin") || u.getRole().equals("Employee")) {
				return uDao.findByUsername(username);
			} else {
				throw new SecurityCheck("You do not have permssion to view this information!"); 
			}
		}catch(SecurityCheck e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean addUser(User u) {
		//TODO: Make sure u is valid
		return uDao.addUser(u);
	}
	
	public boolean registerAccount(double balance, String accountType, User user) {
		if(balance >= 0 && accountType == "Checking" || accountType == "Savings") {
			return uDao.registerAccount(balance, accountType, user);
		}
		return false;
	}
	
}
