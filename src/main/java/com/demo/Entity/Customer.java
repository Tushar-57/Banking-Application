package com.demo.Entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	private Long id;
	
	private String email;
	private String phoneNumber;
	private String name;
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Account acc;
	
	private List<Account> listOfAccount = Collections.<Account>emptyList();

//	Constructor
	public Customer() {

	}
	
	//AddCustomer Constructor
	public Customer(Long id, String email, String phoneNumber, String name, int age) {
		super();
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.age = age;
	}

	//Updating Customer constructor
	public Customer(Long id2, String eMail2, String pNumber, int age2) {
		this.id = id2;
		this.email = eMail2;
		this.phoneNumber = pNumber;
		this.age = age2;
	}

	public Customer(Customer cust) {
		this.id = cust.getId();
		this.email = cust.getEmail();
		this.phoneNumber = cust.getPhoneNumber();
		this.name = cust.getName();
		this.age = cust.getAge();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	// Method for adding the corresponding account to the customer we create
	public void addAccounts(Account acc){
		if(listOfAccount.isEmpty()) {
			this.listOfAccount.add(acc);
		}
		else System.out.println("didn't add");
	}
	

	@Override
	public String toString() {
		return "Customer Id: "+getId()+"\n Customer Name: "+getName()+"\n Customer Email: "+getEmail()+"\n Customer Phone: "+getPhoneNumber()+"\n Age: "+getAge()+"\n User Accounts: "+listOfAccount.toString();           
	}

}
