package com.nagarro.accountservices.services;

import com.nagarro.accountservices.entities.Account;
import com.nagarro.accountservices.entities.Customer;

public interface AccountService  {
	
	public void addMoney(Long customerId,Long balance, Customer Data);
	
	public void withdrawMoney(Long customerId,Long balance,Customer customerData);
	
	public Account getAccountDetails(Long acountNo);
	
	public void deleteAccount(Long accountNo);

	public Account createAccount(Long customerId);

	void deletedbycustomer(Long customerId);
}
