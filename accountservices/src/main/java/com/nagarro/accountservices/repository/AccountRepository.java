package com.nagarro.accountservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.accountservices.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	 Optional<Account> findByCustomerId(Long customerId);
}
