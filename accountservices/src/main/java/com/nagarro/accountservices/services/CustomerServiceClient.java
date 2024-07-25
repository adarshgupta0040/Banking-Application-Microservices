package com.nagarro.accountservices.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.accountservices.entities.Customer;

//@FeignClient(url = "http://localhost:8001",value="CustomerService-Client")
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {
	
	@GetMapping("/customers/{customerId}")
	Customer getCustomerById (@PathVariable Long customerId);
	
}
