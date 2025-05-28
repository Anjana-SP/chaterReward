package com.customer.retailer.serviceImpl;

import com.customer.retailer.entity.Customer;
import com.customer.retailer.entity.Product;
import com.customer.retailer.model.Rewards;

public interface CustomerRetailerService {
	
	public Customer addCustomer(Customer customer);
	public Product addProduct(Product product);
	public Product updateProduct(int id,Product product);
	public Customer updateCustomer(long id,Customer customer);
	
	public Customer getCustomerById(Long id);
	public void deleteCustomer(Long id);
	
	public Rewards getRewardsByCustomerId(Long customerId);
	public Long calculateCurrentMonthPurchaseReward(int customerId) ;

}
