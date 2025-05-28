package com.customer.retailer.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Represents a Customer entity mapped to the "customer" table in the database.
 * This class is used for storing and retrieving customer-related information.
 * 
 * Fields include customer ID, name, email, and phone number.
 * 
 */




@Entity
@Table(name = "customer")
public class Customer {

	@Id
    @Column(name = "CUSTOMER_ID")
	@GeneratedValue
	private long customerId;
	private String cust_name;
	private String cust_email;
	private long phone_number;
	
	
	
	public Customer() {
		super();
	}

	public Customer(long cust_id, String cust_name, String cust_email, long phone_number) {
		super();
		this.customerId = cust_id;
		this.cust_name = cust_name;
		this.cust_email = cust_email;
		this.phone_number = phone_number;
	}
	
	public long getCust_id() {
		return customerId;
	}
	public void setCust_id(long cust_id) {
		this.customerId = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + customerId + ", cust_name=" + cust_name + ", cust_email=" + cust_email
				+ ", phone_number=" + phone_number + "]";
	}
	
	
	
	
}
