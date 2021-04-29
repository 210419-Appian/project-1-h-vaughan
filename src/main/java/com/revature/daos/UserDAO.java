package com.revature.daos;

import java.util.List;


import com.revature.models.User;

public interface UserDAO {
	
	List<User> findAll();
	
	User findByUsername(String username);

	
	boolean addUser(User u);
	
}
