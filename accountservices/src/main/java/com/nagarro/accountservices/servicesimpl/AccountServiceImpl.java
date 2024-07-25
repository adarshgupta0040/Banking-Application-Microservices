package com.nagarro.accountservices.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.accountservices.repository.AccountRepository;
import com.nagarro.accountservices.services.CustomerServiceClient;
import com.nagarro.accountservices.services.AccountService;
import com.nagarro.accountservices.entities.Account;
import com.nagarro.accountservices.entities.Customer;
import com.nagarro.accountservices.exceptions.ValidationException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerServiceClient customerServiceClient;

	@Override
	public Account createAccount(Long customerId) {

		Account account = new Account();
		account.setCustomerId(customerId);
		account.setBalance(0);
		return accountRepository.save(account);
	}

	@Override
	public void addMoney(Long accountNo, Long balance, Customer customerData) {
		Account existingAccount = accountRepository.findById(accountNo)
				.orElseThrow(() -> new ValidationException("Account does not exist"));

		if (existingAccount.getCustomerId() == customerData.getCustomerId()) {

			Customer verifiedData = customerServiceClient.getCustomerById(existingAccount.getCustomerId());

			if (verifiedData.getCustomerName().equals(customerData.getCustomerName())
					&& verifiedData.getEmail().equals(customerData.getEmail()) 
					&& verifiedData.getDob().equals(customerData.getDob())) {

				if (existingAccount != null && balance > 0) {
//		        	System.out.println(existingAccount.getBalance());
					existingAccount.setBalance(existingAccount.getBalance() + balance);
					accountRepository.save(existingAccount);
				} else {
					throw new ValidationException("Account does not exist");
				}
			} else {
				throw new ValidationException("Customer not Verified");
			}
		} else {
			throw new ValidationException("Customerid not Verified");
		}
	}

	@Override
	public void withdrawMoney(Long accountNo, Long balance, Customer customerData) {

		Account existingAccount = accountRepository.findById(accountNo)
				.orElseThrow(() -> new ValidationException("Account does not exist"));

//		System.out.print(existingAccount.getCustomerId() + "+" + withdrawal.getCustomerId());
		if (existingAccount != null) {
			if (existingAccount.getCustomerId() == customerData.getCustomerId()) {

				Customer verifiedData = customerServiceClient.getCustomerById(existingAccount.getCustomerId());

				if (verifiedData.getCustomerName().equals(customerData.getCustomerName())
						&& verifiedData.getEmail().equals(customerData.getEmail())
						&& verifiedData.getDob().equals(customerData.getDob())) {

					if (existingAccount.getBalance() >= balance) {
						existingAccount.setBalance(existingAccount.getBalance() - balance);
						accountRepository.save(existingAccount);
					} else {
						throw new ValidationException("Withdrawal amount exceeds current balance");
					}
				} else {
					throw new ValidationException("Customer not Verified");
				}
			} else {
				throw new ValidationException("Customerid not Verified");
			}
		}
	}

	@Override
	public Account getAccountDetails(Long accountNo) {
		Account existingAccount = accountRepository.findById(accountNo)
				.orElseThrow(() -> new ValidationException("Account does not exist"));
		existingAccount.setCustomerdetails(customerServiceClient.getCustomerById(existingAccount.getCustomerId()));
		return existingAccount;
	}

	@Override
	public void deleteAccount(Long accountNo) {
		Account account = accountRepository.findById(accountNo)
				.orElseThrow(() -> new ValidationException("Account not found"));
		accountRepository.delete(account);
	}

	@Override
	public void deletedbycustomer(Long customerId) {
		Account account = accountRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ValidationException("Account not found"));
		accountRepository.delete(account);
	}

}
