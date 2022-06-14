package com.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.demo.Entity.Customer;
import com.demo.Repository.CustomerRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	//AllCustomers
	public List<Customer> getAllAccounts(){
		List<Customer> allCustomers = customerRepo.findAll();
		if(allCustomers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return allCustomers;
	}
	
	//add Customer
	public ResponseEntity<Customer> addCustomer(Customer customer) {
		return ResponseEntity.ok(customerRepo.save(customer));
	}

	public Customer getCustById(Long id) {
		Optional<Customer> result = customerRepo.findById(id);
		if(!result.isEmpty()) {
			Customer temp = result.get();
			return temp;
		}
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Customer updateCustomer(Long id, ObjectNode objNode) {
		String pNumber = objNode.get("phoneNumber").asText(); 
		String eMail = objNode.get("email").asText();
		int age = objNode.get("age").asInt();
		
		if(id > 0) {
			Customer temp = getCustById(id);
			Customer updatedCustomer = new Customer(id, eMail, pNumber,temp.getName() ,age); 
			customerRepo.save(updatedCustomer);
			return updatedCustomer;
		}
		else
			throw new ResponseStatusException(HttpStatus.CONFLICT);
	}

	public ResponseEntity<Long> deleteById(Long id) {
		customerRepo.deleteById(id);
		return ResponseEntity.ok(id);
	}
	
	
	
	
	

}
