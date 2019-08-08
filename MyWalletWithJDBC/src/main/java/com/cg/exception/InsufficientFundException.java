package com.cg.exception;

/**
 * Custom Exception used by the application on insufficient balance in account
 * @author tanmpath
 * */
public class InsufficientFundException extends Exception{
	/**
	 * We extends Exception class to create the custom exception
	 * Here we check for the validity of amount and minimum balance 
	 * to ensure that we maintain the minimum balance 
	 * */
	private double balance;
	
	public InsufficientFundException() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Handle the insufficient balance exception in the account
	 * @param message the exception message which is given
	 * @param balance the current balance of the account
	 * */
	public InsufficientFundException(String message, double balance) {
		super(message); 
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "InsufficientFundException: [balance="+balance+"]"+super.getMessage();
	}
}
