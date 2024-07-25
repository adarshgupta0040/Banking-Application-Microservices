package com.nagarro.customerservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customerservices.entities.Customer;
import com.nagarro.customerservices.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PutMapping("/update/{customerId}")
	public ResponseEntity<Customer> updateCustomerDetails(@PathVariable Long customerId,
			@RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomerDetails(customerId, customer);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>("Customer Deleted Successfully",HttpStatus.NO_CONTENT);
	}
}
