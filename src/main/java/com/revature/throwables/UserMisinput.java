package com.revature.throwables;

public class UserMisinput extends Exception{

	public UserMisinput() {
		super();
	}
	
	public UserMisinput(String message) {
		super(message);
	}
}