package com.customer.retailer.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.retailer.constants.Constants;
import com.customer.retailer.entity.Customer;
import com.customer.retailer.entity.Product;
import com.customer.retailer.model.Rewards;
import com.customer.retailer.repository.CustomerRepository;
import com.customer.retailer.repository.ProductRepository;

/**
 * Service implementation for managing customer rewards. This class provides
 * methods for adding, updating, and deleting customers and products, as well as
 * calculating rewards for customers based on their transactions.
 */
@Service
public class CustomerRewardsServiceImpl implements CustomerRetailerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	/**
	 * Adds a new customer to the repository.
	 * 
	 * @param customer the customer to be added
	 * @return the added customer
	 */
	@Override
	public Customer addCustomer(Customer customer) {

		customerRepository.save(customer);
		return customer;

	}

	/**
	 * Adds a new product to the repository.
	 * 
	 * @param product the product to be added
	 * @return the added product
	 */
	@Override
	public Product addProduct(Product product) {

		productRepository.save(product);
		return product;

	}

	/**
	 * Updates an existing product in the repository.
	 * 
	 * @param id      the ID of the product to be updated
	 * @param product the updated product details
	 * @return the updated product
	 */
	@Override
	public Product updateProduct(int id, Product product) {

		Product prod = productRepository.getProductById(id);
		prod.setAmount(product.getAmount());
		prod.setProductType(product.getProductType());
		productRepository.save(prod);
		return prod;
	}

	/**
	 * Updates an existing customer in the repository.
	 * 
	 * @param id       the ID of the customer to be updated
	 * @param customer the updated customer details
	 * @return the updated customer
	 */
	@Override
	public Customer updateCustomer(long id, Customer customer) {

		Customer cust = customerRepository.findCustomerById(id);
		cust.setCust_email(customer.getCust_email());
		cust.setCust_name(customer.getCust_name());
		cust.setPhone_number(customer.getPhone_number());
		customerRepository.save(cust);
		return cust;
	}

	/**
	 * Calculates the rewards for a customer based on their transactions in the last
	 * three months.
	 * 
	 * @param customerId the ID of the customer
	 * @return the rewards for the customer
	 */
	public Rewards getRewardsByCustomerId(Long customerId) {

		Timestamp lastMonthTimestamp = getDate(Constants.daysInMonths);
		Timestamp lastSecondMonthTimestamp = getDate(Constants.second * Constants.daysInMonths);
		Timestamp lastThirdMonthTimestamp = getDate(Constants.third * Constants.daysInMonths);

		List<Product> lastMonthTransactions = productRepository.findAllByCustomerId(customerId, lastMonthTimestamp,
				Timestamp.from(Instant.now()));
		List<Product> lastSecondMonthTransactions = productRepository.findAllByCustomerId(customerId,
				lastSecondMonthTimestamp, lastMonthTimestamp);
		List<Product> lastThirdMonthTransactions = productRepository.findAllByCustomerId(customerId,
				lastThirdMonthTimestamp, lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards
				.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;

	}

	/**
	 * Calculates the rewards for a list of transactions in a month.
	 * 
	 * @param lastMonthTransactions the list of transactions
	 * @return the total rewards for the transactions
	 */
	private Long getRewardsPerMonth(List<Product> lastMonthTransactions) {

		return lastMonthTransactions.stream().mapToLong(reward -> calculateRewards(reward)).sum();
	}

	/**
	 * Calculates the rewards for a single transaction.
	 * 
	 * @param p the product transaction
	 * @return the rewards for the transaction
	 */
	private Long calculateRewards(Product p) {

		if (p.getAmount() > Constants.firstRewardLimit && p.getAmount() <= Constants.secondRewardLimit) {
			return Math.round(p.getAmount() - Constants.firstRewardLimit);
		} else if (p.getAmount() > Constants.secondRewardLimit) {
			return Math.round(p.getAmount() - Constants.secondRewardLimit) * 2
					+ (Constants.secondRewardLimit - Constants.firstRewardLimit);
		} else
			return 0l;

	}

	/**
	 * Calculates the rewards for a customer's transactions in the current month.
	 * 
	 * @param customerId the ID of the customer
	 * @return the total rewards for the current month
	 */
	@Override
	public Long calculateCurrentMonthPurchaseReward(int customerId) {

		Timestamp lastMonthTimestamp = getDate(Constants.daysInMonths);
		List<Product> lastMonthTransactions = productRepository.findAllByCustomerId(customerId, lastMonthTimestamp,
				Timestamp.from(Instant.now()));
		return getRewardsPerMonth(lastMonthTransactions);
	}

	/**
	 * Retrieves a customer by their ID.
	 * 
	 * @param id the ID of the customer
	 * @return the customer with the specified ID
	 */
	@Override
	public Customer getCustomerById(Long id) {

		return customerRepository.findCustomerById(id);
	}

	/**
	 * Deletes a customer from the repository.
	 * 
	 * @param id the ID of the customer to be deleted
	 */
	@Override
	public void deleteCustomer(Long id) {

		customerRepository.deleteById(id);

	}

	/**
	 * Helper method to get a timestamp for a specified number of days in the past.
	 * 
	 * @param d the number of days
	 * @return the timestamp for the specified number of days in the past
	 */
	private Timestamp getDate(int d) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(d));
	}

}
