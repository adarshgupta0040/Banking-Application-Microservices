package com.nagarro.customerservices.services;

import java.util.List;

import com.nagarro.customerservices.entities.Customer;

public interface CustomerService {
	
	 public Customer addCustomer(Customer customer);
	 
	 public List<Customer> getAllCustomers();
	 
	 public Customer getCustomerById(Long customerId);
	 
	 public Customer updateCustomerDetails(Long customerId, Customer customer);
	 
	 public void deleteCustomer(Long customerId);
	 
}