package com.revature.throwables;

public class BalanceBelowZero extends Exception{

	public BalanceBelowZero() {
		super();
	}
	
	public BalanceBelowZero(String message) {
		super(message);
	}
}
