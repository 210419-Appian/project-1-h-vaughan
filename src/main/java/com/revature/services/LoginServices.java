package com.revature.services;

import com.revature.models.User;

public class LoginServices {
	
	private UserServices uService = new UserServices();
	private User fakeAdmin = new User("fakeUsername", "fakePassword", "Not", "Real", "notarealemail@adress.com", "Admin");
	
	public boolean login(String username, String password) {
		User myUser = uService.findByUsername(username, fakeAdmin);
		
		if (myUser != null) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
