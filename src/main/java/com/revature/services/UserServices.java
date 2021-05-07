package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.throwables.SecurityCheck;

public class UserServices {
	private static UserDAOImpl uDao = new UserDAOImpl();
		
	public List<User> findAll() {
		return uDao.findAll();
	}
	
	public User findByUsername(String username) {
		return uDao.findByUsername(username);
	}
	
	public User findById(int id) {
		return uDao.findByID(id);
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
	
	public boolean updateUser(User u, int id) {
		//return false if u.getUsername() is not null in the database, check id and u2.getUserID. If they are the same, is true, if different return false
		User u2 = findByUsername(u.getUsername());
		if(u2 != null) {
			if(u2.getUserID() == id) {
				return uDao.updateUser(u, id);				
			}else {
				return false;
			}
		}else {
			return uDao.updateUser(u, id);
		}

	}
	
}
