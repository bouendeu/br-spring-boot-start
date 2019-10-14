package co.raphy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.raphy.entity.Customer;
import co.raphy.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired
	private CustomerRepository repo;

	@GetMapping
	public Iterable<Customer> getCustomers(@RequestParam(name = "_page", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "_limit", defaultValue = "10") Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return repo.findAll(pageable).getContent();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id) {
		try {
			Customer cust = repo.findById(id).get();
			return ResponseEntity.ok(cust);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return ResponseEntity.ok(customer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		repo.save(customer);
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
		try {
			Customer cust = repo.findById(id).get();
			repo.delete(cust);
			return ResponseEntity.ok(cust);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}
}
