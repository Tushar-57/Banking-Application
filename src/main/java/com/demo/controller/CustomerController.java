package com.demo.controller;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Entity.Account;
import com.demo.Entity.Customer;
import com.demo.Service.AccountService;
import com.demo.Service.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class CustomerController {
	
	LoggerFactory log = new LoggerFactory();
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/allCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllAccounts());
	}
	
	@PostMapping("addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer cust){
		return customerService.addCustomer(cust);
	}
	
	//Adding a customer with account.
	@PostMapping("addAccCustomer")
	public ResponseEntity<ObjectNode> addAccountCustomer(@RequestBody ObjectNode objNode) throws Exception{
		if(objNode.isEmpty()) {
			throw new Exception("EMPTY !");
		}
		//Jackson to help in mapping.
		ObjectMapper objMapper =  new ObjectMapper();
		
//		Taking out the account object for seperate obj creation.
		JsonNode test = objNode.get("account");
		
//		Creating customer object, Ignoring Account Object in it for now. 
		Customer cust = objMapper.readValue(objNode.toString(), Customer.class);
		
		//GEtting the values from request body 
		int balance = test.findValue("balance").asInt();
		String accType = test.findValue("accountType").asText();
		Account account = new Account(accType, balance);
		accountService.addAccount(account);
		
		//add Customer object 
		customerService.addCustomer(cust);
		
//		To add the above created account to the list in Customer class for accounts
//		cust.addAccounts(account);  //Giving error "UnSupportedOperationExcep"
		
		System.out.println("Customer Created: "+cust.getId()+" With Account Type: "+accType+" with initial balance of: "+balance+" & the list of accounts is: ");
		return  ResponseEntity.ok(objNode);
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerService.getCustById(id);
	}
	
	@PutMapping("/updateCustomer/{id}") //In this requirenment we dont want to pass the comlete customer object thats why we use ObjectNode
	public Customer updateCustomer(@PathVariable Long id, @RequestBody ObjectNode objNode){
		return customerService.updateCustomer(id, objNode);
	}
	
	@DeleteMapping("deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteById(id);
		
	}
	

}
