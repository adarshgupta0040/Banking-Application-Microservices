package com.nagarro.customerservices.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.customerservices.entities.Customer;
import com.nagarro.customerservices.exception.ValidationException;
import com.nagarro.customerservices.repository.CustomerRepository;
import com.nagarro.customerservices.services.AccountServiceClient;
import com.nagarro.customerservices.services.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountServiceClient accountServiceClient;

	public Customer addCustomer(Customer customer) {

		Customer addedCustomer = customerRepository.save(customer);
		accountServiceClient.createAccountForCustomer(customer.getCustomerId());
		return addedCustomer;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ValidationException("Customer not found"));
	}

	public Customer updateCustomerDetails(Long customerId, Customer customer) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ValidationException("Customer not found"));

		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPhoneNo(customer.getPhoneNo());
		existingCustomer.setDob(customer.getDob());

		return customerRepository.save(existingCustomer);
	}

	public void deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ValidationException("Customer not found"));

		accountServiceClient.deleteAccountbycustomer(customer.getCustomerId());
		customerRepository.delete(customer);

	}
}
