package com.revature.models;

public class WithdrawRequest {

	private int accountId;
	private double amount;
	
	public WithdrawRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WithdrawRequest(int accountId, double amount) {
		super();
		this.accountId = accountId;
		this.amount = amount;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		WithdrawRequest other = (WithdrawRequest) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WithdrawRequest [accountId=" + accountId + ", amount=" + amount + "]";
	}
	
	
	
}
