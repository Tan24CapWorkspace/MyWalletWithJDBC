package com.cg.service;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

import java.util.List;
import java.util.Map;

public interface Transaction {
	public double withdraw(Account a, double amount) throws InsufficientFundException;
	public double deposit(Account a, double amount) throws InsufficientFundException;
	public boolean transferMoney(Account from, Account to, double amount) throws InsufficientFundException;
	public boolean addAccount(Account a) throws InsufficientFundException;
	public boolean deleteAccount(Account a);
	public Account findAccount(long mobile);
	public Map<Long, Account> getAllAccount();
	public boolean updateAccount(Account a);
	public boolean findaccountid(Integer id);
}
