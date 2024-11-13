package com.schoolofnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolofnet.entity.Customer;
import com.schoolofnet.entity.repository.CustomerRepository;


@Controller

@RequestMapping(path = "/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}
	
	@PostMapping
	@ResponseBody
	
	public Customer create(@RequestParam("name") String name, @RequestParam("age") Integer age) {
		Customer customer = new Customer(name,age);
		repository.save(customer);
		
		
		return customer;
	}
	
	
	

}
