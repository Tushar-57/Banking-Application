package com.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.demo.Entity.Account;
import com.demo.Repository.AccountRepository;

@Component
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // get all accounts in system
    public List<Account> getAllAccounts() {
        System.out.println("Fetching all Accounts");
        return (List<Account>) this.accountRepository.findAll();
    }

    //Adding account
	public Account addAccount(Account acc) {
		if(acc.accountTypes.contains(acc.getAccountType().toUpperCase())) {
			accountRepository.save(acc);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Check Account Type !");
		}
		System.out.println("Account-ID: "+acc.getAccountNumber());
		return acc;
	}

	public Account getAccountById(int id) {
		Optional<Account> acc = accountRepository.findById(id);
		return acc.get();
	}
}
