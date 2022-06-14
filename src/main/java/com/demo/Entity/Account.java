package com.demo.Entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Account {

	public List<String> accountTypes = Arrays.asList("CURRENT", "SALARY", "SAVINGS", "LOAN");
	
    public int accountNumber;
	
    public String accountType;
    public int balance;
    
    //We can have a check against this accountTypes, while creating account, if it's right one!
    
    

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Customer> customers;
    
    public Account() {}

	public Account(String accType, int balance) {
		super();
		this.accountNumber = getAccountNumber();
		this.accountType = accType;
		this.balance = balance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int id) {
		this.accountNumber = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

    
    
}
