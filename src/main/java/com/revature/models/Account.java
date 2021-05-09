package com.revature.models;

public class Account {

	private int accountID; 
	private double balance; 
	private String status;
	private String accountType;
	private double interestRate;
	private int owner;
	
	public Account() {
		super();
	}
	
	public Account(double balance, String status, String accountType, double interestRate, int owner) {
		super();		
		this.balance = balance;
		this.status = status;
		this.accountType = accountType;
		if(this.accountType.equals("Checking")) {
			this.interestRate = 0;
		}else if(this.accountType.equals("Savings")) {
			this.interestRate = interestRate;
		}
		this.owner = owner;
	}
	
	public Account(int accountID, double balance, String status, String accountType, double interestRate, int owner) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.status = status;
		this.accountType = accountType;		
		if(this.accountType.equals("Checking")) {
			this.interestRate = 0;
		}else if(this.accountType.equals("Savings")) {
			this.interestRate = interestRate;
		}
		this.owner = owner;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(interestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + owner;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (Double.doubleToLongBits(interestRate) != Double.doubleToLongBits(other.interestRate))
			return false;
		if (owner != other.owner)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", status=" + status + ", accountType="
				+ accountType + ", interestRate=" + interestRate + ", owner=" + owner + "]";
	}


	

}
