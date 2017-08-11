package com.joonkim.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.joonkim.spring.dao.CustomerDAO;
import com.joonkim.spring.model.Customer;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") int id) {

		Customer customer = customerDAO.getCustomerByID(id);
		
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	
	@GetMapping("/allcustomers")
	public List<Customer> getAllCustomers(){
		return customerDAO.getAllCustomers();
	}
	
	@PostMapping(value = "/customers")
	public ResponseEntity createCustomer(@RequestHeader("id") int id, @RequestHeader("fname") String firstName, 
			@RequestHeader("lname") String lastName, @RequestHeader("email") String email, @RequestHeader("mobile") String mobile) {
		
		Customer customer = new Customer(id, firstName, lastName, email, mobile);
		
		customerDAO.saveCustomer(customer);
		
		return new ResponseEntity(customer, HttpStatus.OK);
	}
}

