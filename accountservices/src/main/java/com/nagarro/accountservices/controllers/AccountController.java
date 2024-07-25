package com.nagarro.accountservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.accountservices.entities.Account;
import com.nagarro.accountservices.entities.Customer;
import com.nagarro.accountservices.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
    @PostMapping("/create/{customerId}")
    public ResponseEntity<Account> createAccountForCustomer(@PathVariable Long customerId) {
        Account account = accountService.createAccount(customerId);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/add-money/{accountNo}/{balance}")
    public ResponseEntity<String> addMoneyToAccount(@PathVariable Long accountNo,@PathVariable Long balance,@RequestBody Customer data) {
        accountService.addMoney(accountNo,balance,data);
        return new ResponseEntity<>("Amount Credited Successfully",HttpStatus.OK);
    }
    
    @PostMapping("/withdraw-money/{accountNo}/{balance}")
    public ResponseEntity<String> withdrawMoneyFromAccount(@PathVariable Long accountNo,@PathVariable Long balance,@RequestBody Customer data) {
        accountService.withdrawMoney(accountNo,balance,data);
        return new ResponseEntity<>("Amount Debited Successfully", HttpStatus.OK);
    }
    
    @GetMapping("/{accountNo}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountNo) {
        Account accountDetails = accountService.getAccountDetails(accountNo);
        return new ResponseEntity<>(accountDetails, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{accountNo}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNo) {
        accountService.deleteAccount(accountNo);
        return new ResponseEntity<>("Account Deleted",HttpStatus.OK);
    }
    
    @DeleteMapping("/delete-it/{customerId}")
    public ResponseEntity<Void> deleteAccountByCustomer(@PathVariable Long customerId) {
        accountService.deletedbycustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
