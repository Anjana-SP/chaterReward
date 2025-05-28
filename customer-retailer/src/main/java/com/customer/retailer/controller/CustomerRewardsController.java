package com.customer.retailer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.retailer.entity.Customer;
import com.customer.retailer.entity.Product;
import com.customer.retailer.exception.CustomerNotFoundException;
import com.customer.retailer.model.Rewards;
import com.customer.retailer.repository.CustomerRepository;
import com.customer.retailer.serviceImpl.CustomerRewardsServiceImpl;

/**
 * REST controller for managing customer rewards. Provides endpoints for adding,
 * updating, retrieving, and deleting customers and products, as well as
 * calculating reward points.
 */

@RestController
@RequestMapping("/charter")
public class CustomerRewardsController {

	@Autowired
	CustomerRewardsServiceImpl service;

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Adds a new customer.
	 *
	 * @param customer the customer to add
	 * @return a ResponseEntity containing the added customer and HTTP status code
	 */
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {

			Customer customer1 = service.addCustomer(customer);
			return new ResponseEntity<>(customer1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Adds a new product.
	 *
	 * @param product the product to add
	 * @return a ResponseEntity containing the added product and HTTP status code
	 */

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		try {
			Product product1 = service.addProduct(product);
			return new ResponseEntity<>(product1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates an existing product.
	 *
	 * @param id      the ID of the product to update
	 * @param product the updated product details
	 * @return a ResponseEntity containing a message and HTTP status code
	 */
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
		Product product1 = null;
		product1 = service.updateProduct(id, product);
		if (product1 != null)
			return new ResponseEntity<>("Updated", HttpStatus.OK);
		else
			return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Updates an existing customer.
	 *
	 * @param id       the ID of the customer to update
	 * @param customer the updated customer details
	 * @return a ResponseEntity containing a message and HTTP status code
	 */

	@PutMapping("/customer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		Customer customer1 = null;
		customer1 = service.updateCustomer(id, customer);
		if (customer1 != null)
			return new ResponseEntity<>("Updated", HttpStatus.OK);
		else
			return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Retrieves the current month's reward points for a customer.
	 *
	 * @param customerId the ID of the customer
	 * @return the current month's reward points
	 */
	@GetMapping("/getCurrentMonthRewardPoints/{customerId}")
	public Long getCurrentMonthPurchaseReward(@PathVariable int customerId) {
		return service.calculateCurrentMonthPurchaseReward(customerId);
	}

	/**
	 * Retrieves the total reward points for a customer for the period of three
	 * months.
	 *
	 * @param customerId the ID of the customer
	 * @return a ResponseEntity containing the customer's rewards and HTTP status
	 *         code
	 */
	@GetMapping(value = "/totalRewardPoints/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
		Customer customer = customerRepository.findCustomerById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Invalid  customer Id ");
		}
		Rewards customerRewards = service.getRewardsByCustomerId(customerId);
		return new ResponseEntity<>(customerRewards, HttpStatus.OK);
	}

	/**
	 * Deletes a customer.
	 *
	 * @param id the ID of the customer to delete
	 * @return a ResponseEntity containing a message and HTTP status code
	 */
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		Customer customer = service.getCustomerById(id);
		if (customer != null) {
			service.deleteCustomer(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

	}

}
