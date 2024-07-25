package com.nagarro.customerservices.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(url = "http://localhost:8002",value="AccountService-Client")
@FeignClient(name ="ACCOUNT-SERVICE")
public interface AccountServiceClient {
	
	@PostMapping("/accounts/create/{customerId}")
	void createAccountForCustomer(@PathVariable Long customerId);
	
	@DeleteMapping("/accounts/delete-it/{customerId}")
	void deleteAccountbycustomer(@PathVariable Long customerId);

}
