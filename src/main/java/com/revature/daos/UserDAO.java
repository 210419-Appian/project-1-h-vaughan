package com.revature.daos;

import java.util.List;


import com.revature.models.User;

public interface UserDAO {
	
	List<User> findAll();
	
	User findByUsername(String username);
	
	User findByID(int id);
	
	boolean addUser(User u);
	
	boolean updateUser(User u, int id);
	
	boolean deleteUser(User targetUser);

	boolean registerCheckingAccount(double balance, User user);
	
	boolean registerSavingsAccount(double balance, double interestRate, User user);
	
}
