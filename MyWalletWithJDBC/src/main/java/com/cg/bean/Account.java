// File: Account.java
// Account class with get and se methods

package com.cg.bean;

/**
 * @author tanmpath
 * */

/**
 * This is a model class to hold the account information
 * 
 * @author tanmpath
 * */

public class Account {
	
	private int aid; // Maintain the account id of the account holder.
	
	private long mobile; //  mobile: Hold the Mobile Number of the account holder,
						 //  It also works as key to find the account from the collection
	
	private String accountholder; // Hold the account holder name.

	private double balance; // Maintain the balance the account holder.
	
	
	/**
	 * No-argument constructor initializes instance variables
	 * to null
	 * */
	
	public Account() { 
	}
	
	
	/**
	 * Account constructor
	 * @param aid : A 3-digit account id
	 * @param mobile2 is account holder mobile number
	 * @param accountholder is account holder name
	 * @param balance is account holder balance
	 * */
	
	public Account(int aid, long mobile2, String accountholder, double balance) {
		super();
		this.aid = aid;
		this.mobile = mobile2;
		this.accountholder = accountholder;
		this.balance = balance;
	}
	
	
	// Default generated Getters and setters
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAccountholder() {
		return accountholder;
	}
	public void setAccountholder(String accountholder) {
		this.accountholder = accountholder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}