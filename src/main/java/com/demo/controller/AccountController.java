package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.Entity.Account;
import com.demo.Service.AccountService;

@RestController
public class AccountController {
	
	@Autowired(required = true)
	private AccountService accService;
	
	@GetMapping("/allAccounts")
	public List<Account> getAllAccount(){
		return accService.getAllAccounts();
	}
	
	//Just for adding account.
	@PostMapping("/accounts")
	public ResponseEntity<Account> postAccount(@RequestBody Account acc){
		if(acc.accountTypes.contains(acc.getAccountType().toUpperCase())) {
			this.accService.addAccount(acc);
		}
		
		return ResponseEntity.ok(acc);
	}
	
	@GetMapping("/accounts/{id}")
	public Account getAccById(@PathVariable int id) {
		return accService.getAccountById(id);
	}
	
//	For Account Operation get balance
	@GetMapping("/accounts/getBalance/{id}")
	public int getBalance(@PathVariable int id) {
		Account acc = accService.getAccountById(id);
		if(acc.getAccountType().equals("LOAN")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't Fetch Balance from LOAN A/C");
		}else 	return acc.getBalance();
	}
	
	
}
