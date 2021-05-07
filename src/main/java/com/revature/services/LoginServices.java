package com.revature.services;

import com.revature.models.User;

public class LoginServices {
	
	private UserServices uService = new UserServices();
	
	public boolean login(String username, String password) {
		User myUser = uService.findByUsername(username);
		
		if (myUser != null) {
			if (myUser.getPassword().equals(password)) {
				return true;
			}
			return false;
		}else {
			return false;
		}
		
	}
	
}
