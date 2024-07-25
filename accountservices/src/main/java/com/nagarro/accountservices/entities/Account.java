package com.nagarro.accountservices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accountdetails")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long AccountNo;
	
	private long Balance;
	
	private long customerId;
	
	transient private Customer customerdetails;

	public Long getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(Long accountNo) {
		AccountNo = accountNo;
	}

	public long getBalance() {
		return Balance;
	}

	public void setBalance(long balance) {
		Balance = balance;
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public Customer getCustomerdetails() {
		return customerdetails;
	}

	public void setCustomerdetails(Customer customerdetails) {
		this.customerdetails = customerdetails;
	}
	
}
