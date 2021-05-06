package com.revature.daos;

import java.util.List;


import com.revature.models.User;

public interface UserDAO {
	
	List<User> findAll();
	
	User findByUsername(String username);
	
	User findByID(int id);
	
	boolean addUser(User u);
	
	boolean registerAccount(double balance, String accountType, User user);
	
	boolean updateUser(User u, int id);
	
}
