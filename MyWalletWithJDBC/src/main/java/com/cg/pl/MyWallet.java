package com.cg.pl;

/**
 * @author tanmpath
 * */

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;
import com.cg.service.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyWallet {

	// creating account service
	private static AccountServiceImpl service = new AccountServiceImpl();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// providing options
		while (true) {
			System.out.println("=========================MENU=====================");
			System.out.println("1. Create New Account");
			System.out.println("2. Money Transaction Operation");
			System.out.println("3. Delete Account");
			System.out.println("4. Find Account");
			System.out.println("5. Display Accounts");
			System.out.println("6. EXIT");

			// take the choice
			String choice = br.readLine();

			switch (choice) {

			case "1": // creating account
				Integer id = 0;
				Long mobile = null;
				String holder = null;
				Double balance = null;
				Account acc = null;

				// Getting mobile number from user
				mobile = getMobile();

				// checking that user successfully enter a valid mobile number
				if (mobile != null) {

					// Checking that account already exist or not
					if (service.findAccount(mobile) == null) {

						id = getAccountID();

						// checking that id is successfully entered
						if (id != null) {

							holder = getHolderName();

							// checking that account holder name is successfully entered
							if (holder != null) {

								balance = getBalance();

								// checking that account holder balance is successfully entered
								if (balance != null) {

									acc = new Account(id, mobile, holder, balance);
									try {
										if (service.addAccount(acc)) {
											System.out.println("Account Added");
										} else {
											System.out.println("Account Not Added");
										}
									} catch (InsufficientFundException e) {
										System.out.println(e.getMessage());
									}
								}

							}
						}

					} else {
						System.out.println("Account Already Exist");
					}
				}

				// break of case 1
				break;

			// ----------------------------------------------------------------------------------------------------------------
			case "2": // update operation

				mobile = getMobile();
				acc = service.findAccount(mobile);

				// Checking that account EXIST or not
				if (acc != null) {
					choice = "0";

					// Providing options
					System.out.println("1. Withdraw amount");
					System.out.println("2. Deposit money");
					System.out.println("3. Transfer Money");
					System.out.println("Enter choice");
					choice = br.readLine();

					switch (choice) {
					case "1": // withdraw
						try {
							String amount = null;
							System.out.println("Enter the amount");
							amount = br.readLine();
							// Validating that user enter a valid amount
							if (Validator.validateData(amount, Validator.balancePattern)) {
								service.withdraw(acc, Double.parseDouble(amount));
								System.out.println("Transaction completed");
							} else {
								System.out.println("Invalid amount entered");
							}
						} catch (InsufficientFundException e) {
							System.out.println(e.getMessage());
						}
						break; // break of case 1

					case "2": // deposit
						System.out.println("Enter the amount");
						try {
							String amount = null;
							amount = br.readLine();
							if (Validator.validateData(amount, Validator.balancePattern)) {
								service.deposit(acc, Double.parseDouble(amount));
								System.out.println("Transaction completed");
							} else {
								System.out.println("Invalid amount entered");
							}
						} catch (InsufficientFundException e) {
							System.out.println(e.getMessage());
						}
						break; // break of case 2

					case "3": // transfer money
						Account from = acc;
						Account to = null;

						System.out.println("Enter Mobile no. of account where money to transfer");
						while (true) {
							mobile = getMobile();
							if(mobile != from.getMobile()) {
								acc = service.findAccount(mobile);
								if (acc != null) {
									to = acc;
									break;
								} else {
									System.out.println("Account not found\nRe-Enter mobile no.");
								}
							}else {
								System.out.println("You can not transfer to same account");
							}
						}

						System.out.println("Enter Amount");
						try {
							String amount = null;
							amount = br.readLine();
							if (Validator.validateData(amount, Validator.balancePattern)) {
								service.transferMoney(from, to, Double.parseDouble(amount));
								System.out.println("Amount Transferred");
							} else {
								System.out.println("Invalid amount entered");
							}
						} catch (InsufficientFundException e) {
							System.out.println(e.getMessage());
						}
						break; // break of case 3

					default:
						System.out.println("Invalid choice");

					}

				} else {
					System.out.println("Account Not found");
				}
				break; // break of case 2

			// ----------------------------------------------------------------------------------------------------------------
			case "3": // delete

				mobile = getMobile();
				if (mobile != null) {
					acc = service.findAccount(mobile);
					if (acc != null) {
						service.deleteAccount(acc);
						System.out.println("Account Deleted");
					} else {
						System.out.println("Account not found");
					}
				}
				break; // break of case 3

			// ----------------------------------------------------------------------------------------------------------------
			case "4": // find

				mobile = getMobile();
				if (mobile != null) {
					acc = service.findAccount(mobile);
					if (acc != null) {
						System.out.println("Account found.");
						service.printStatement(acc);
					} else {
						System.out.println("Account not found");
					}
				}
				break; // break of case 4

			// ----------------------------------------------------------------------------------------------------------------
			case "5": // display all accounts
				System.out.println("Current Account present");
				Map<Long, Account> list = service.getAllAccount();
				if (list.size() == 0) {
					System.out.println("No Account Present");
				} else {
					List<Account> l = new ArrayList(list.values());
					for (Account a : l) {
						service.printStatement(a);
						System.out.println();
					}
				}
				break;

			case "6": // exit from the system
				System.exit(0);
				break;

			default: // for invalid choice
				System.out.println("Invalid choice");

			} // end of switch
			System.out.println("\n\n");
		} // end of while

	} // emd of main

	
	/**
	 * To get a valid mobile number from user
	 * 
	 * @return Long : Valid mobile number
	 */
	private static Long getMobile() throws IOException {
		long mobile;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Mobile no.");
		while (true) {
			String s_m = br.readLine();
			if (Validator.validateData(s_m, Validator.mobilePattern)) {
				try {
					mobile = Long.parseLong(s_m);
					return mobile;
				} catch (NumberFormatException e) {
					System.out.println("Invalid Mobile no");
					System.out.println("Re-Enter the Mobile no.");
				}
			} else {
				System.out.println("Invalid Mobile no");
				System.out.println("Re-Enter the Mobile no.");
			}
		}
	}

	
	
	/**
	 * To get a valid Account ID from user
	 * 
	 * @return Integer : Valid Account ID
	 */

	private static Integer getAccountID() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer id = null;
		// adding account no
		System.out.println("Enter Account No.");
		while (true) {
			String s_id = br.readLine();
			if (Validator.validateData(s_id, Validator.aIdPattern)) {
				try {
					id = Integer.parseInt(s_id);
					if (!service.findaccountid(id))
						break;
					else {
						System.out.println("Account ID already present");
						System.out.println("Re Enter Account ID");
					}

				} catch (NumberFormatException e) {
					System.out.println("ID should be numeric.");
				}
			} else {
				System.out.println("Invalid ID.");
			}
		}
		return id;
	}

	
	
	/**
	 * To get valid Account Holder Name
	 * 
	 * @return String : Valid Account holder name
	 */
	private static String getHolderName() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String holderName = null;

		// account holder name
		System.out.println("Enter Holder name");
		while (true) {
			String s_name = br.readLine();
			if (Validator.validateData(s_name, Validator.namePattern)) {
				try {
					holderName = s_name;
					break;
				} catch (NumberFormatException e) {
					System.out.println("Invalid Name");
					System.out.println("Re-Enter the Name.");
				}
			} else {
				System.out.println("Invalid Name");
				System.out.println("Re-Enter the Name.");
			}
		}
		return holderName;
	}

	
	
	
	/**
	 * To get the valid balance
	 * 
	 * @return Double : valid balance
	 */
	private static Double getBalance() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Double balance = null;

		// account balance
		System.out.println("Enter Account balance");
		while (true) {
			String s_bal = br.readLine();
			if (Validator.validateData(s_bal, Validator.balancePattern)) {
				try {
					balance = Double.parseDouble(s_bal);
					break;
				} catch (NumberFormatException e) {
					System.out.println("Invalid Balance");
					System.out.println("Re-Enter the Balance");
				}
			} else {
				System.out.println("Invalid Balance");
				System.out.println("Re-Enter the Balance");
			}
		}
		return balance;
	}

}