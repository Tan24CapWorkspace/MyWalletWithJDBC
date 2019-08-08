package com.cg.service;

/**
 * @author tanmpath
 * */
import java.util.Map;
import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;

public class AccountServiceImpl implements Gst, Transaction { 

	private AccountDAO dao = new AccountDAOImpl();	// DAO object to communicate with DAO layer
	
	
	public void setAccountDAOImpl(AccountDAO mockdao) {
		this.dao = mockdao;		
	}
	
	/**
	 * To withdraw the amount from the account
	 * @param account account from which the amount to be deducted
	 * @param amount the amount to be deducted
	 * @return updated balance
	 * @throws InsufficientFundException: Insufficient Fund
	 * */
	@Override
	public double withdraw(Account account, double amount) throws InsufficientFundException {
		
		double new_balance = account.getBalance() - amount;
		if (new_balance < 1000.00) {
			new_balance = account.getBalance();
			throw new InsufficientFundException("Insufficient Fund: Can not process the withdraw", new_balance);
		}
		account.setBalance(new_balance);
		dao.updateAccount(account);
		return new_balance;
	}

	
	/**
	 * To deposit the amount to the account
	 * @param account account in which amount to be credited
	 * @param amount the amount to be credited
	 * @throws InsufficientFundException: Insufficient Fund
	 * */
	@Override
	public double deposit(Account account, double amount) throws InsufficientFundException{
		// TODO Auto-generated method stub
		if(amount < 0.0) throw new InsufficientFundException("Insufficient Fund: Can not process the Deposit", amount);
		double new_balance = account.getBalance() + amount;
		account.setBalance(new_balance);
		dao.updateAccount(account);
		return new_balance;
	}

	/**
	 * Transfer amount from one account to another account
	 * @param from: account from which the amount to be deducted
	 * @param to: account to which amount will be credited
	 * @return boolean: true - if transaction is successful / false - if transaction is unsuccessful
	 * @throws InsufficientFundException: Insufficient Fund
	 * */
	@Override
	public boolean transferMoney(Account from, Account to, double amount) throws InsufficientFundException{// INCOMPLETE
		// TODO Auto-generated method stub
		double new_balance = from.getBalance() - amount;
		if (!(new_balance < 1000.00)) {
			from.setBalance(new_balance);
			dao.updateAccount(from);
			to.setBalance(to.getBalance() + amount);
			dao.updateAccount(to);
			return true;
		} else {
			throw new InsufficientFundException("Insufficient Fund: Can not process the transfer", amount);
		}
	}

	
	/**
	 * Calculate the tax on the present balance on the given tax rate
	 * @param PCT: tax rate
	 * @param amount: amount on which tax is calculated
	 * @return calculated tax
	 * */
	@Override
	public double calculateTax(double PCT, double amount) {
		return amount * Gst.PCT_5;
	}

	
	/**
	 * Add new account to the collection
	 * @param acc: account to be inserted
	 * @return boolean: true - if transaction is successful / false - if transaction is unsuccessful
	 * */
	@Override
	public boolean addAccount(Account acc) throws InsufficientFundException{	
		if(acc == null) return false;
		if(acc.getBalance()<1000) {
			throw new InsufficientFundException("The minimum balance must be 1000",acc.getBalance());
		}else {
			return dao.addAccount(acc);
		}
	}

	
	/**
	 * Delete account from collection
	 * @param acc: account to be deleted
	 * @return boolean: true - if transaction is successful / false - if transaction is unsuccessful
	 * */
	@Override
	public boolean deleteAccount(Account acc) {
		return dao.deleteAccount(acc);
	}

	/**
	 * Retrieve account using mobile number from collection
	 * @param mobile: to fetch the account associated with this mobile number
	 * @return account that is linked to the given mobile number
	 * */
	@Override
	public Account findAccount(long mobile) {
		return dao.findAccount(mobile);
	}

	
	/**
	 * Retrieve all the account
	 * @return Whole collection of accounts
	 * */
	@Override
	public Map<Long, Account> getAllAccount() {
		return dao.getAccList();
	}

	/**
	 * Update the account
	 * @param acc: account to be update
	 * @return boolean: true - if transaction is successful / false - if transaction is unsuccessful
	 * */
	@Override
	public boolean updateAccount(Account acc) {
		if(dao.updateAccount(acc) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * Checking that account id already exist or not
	 * @param id : given account id
	 * @return true - if occupied / false - if not occupied 
	 * */
	@Override
	public boolean findaccountid(Integer id){
		return dao.findAccountID(id);
	}
	
	

	/**
	 * Printing the account details
	 * @param a: account to be print
	 * */
	public void printStatement(Account a) {
		System.out.println("Account ID : "+a.getAid());
		System.out.println("Account Holder Name : "+a.getAccountholder());
		System.out.println("Account Mobile No : "+a.getMobile());
		System.out.println("Account Balance : "+a.getBalance());
	}
	
}