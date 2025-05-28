package com.customer.retailer.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Represents a Product entity mapped to a database table. This class stores
 * information about a product transaction, including product type, amount,
 * transaction date, and associated customer ID.
 * 
 */

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int productId;
	private String productType;
	private double amount;
	private Timestamp transactionDate;

	@Column(name = "customer_id")
	private long cust_id;

	public Product() {
		super();
	}

	public Product(int productId, String productType, double amount, Timestamp transactionDate, long cust_id) {
		super();
		this.productId = productId;
		this.productType = productType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.cust_id = cust_id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getCust_id() {
		return cust_id;
	}

	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}

}
