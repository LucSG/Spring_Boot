package com.schoolofnet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.schoolofnet.entity.Customer;
import com.schoolofnet.entity.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerRepository repository;

	@Autowired
	public CustomerController(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}

	@GetMapping
	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findOne(@PathVariable("id") Long id) {
		Optional<Customer> customer = repository.findById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path = "/name/{name}")
	public Customer findByName(@PathVariable("name") String name) {
		return repository.findByName(name);
	}

	@PostMapping
	public ResponseEntity<Customer> create(@RequestParam("name") String name, @RequestParam("age") Integer age) {
		if (name == null || name.isEmpty() || age == null || age <= 0) {
			return ResponseEntity.badRequest().build();
		}
		Customer customer = new Customer(name, age);
		repository.save(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);

	
	}
	
	@DeleteMapping(path = "/{id}")
	public void destroy(@PathVariable("id") Long id) {
		Optional<Customer> customer = repository.findById(id);
		
		if(customer.isPresent()) {
			repository.delete(customer.get());
		}
	}
}
