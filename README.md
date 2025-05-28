# chaterReward Service
Calculating the reward points earned for each customer per month and total. 

## Overview
This is a Spring Boot service for managing customer rewards. The service provides methods for adding, updating, and deleting customers and products, as well as calculating rewards for customers based on their transactions.

## Reward System
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives:
- 2 points for every dollar spent over $100 in each transaction
- 1 point for every dollar spent between $50 and $100 in each transaction

For example, a $120 purchase = 2x$20 + 1x$50 = 90 points.

## Features
- Add a new customer
- Add a new product
- Update an existing customer
- Update an existing product
- Calculate rewards for a customer based on their transactions in the last three months
- Calculate rewards for a customer's transactions in the current month
- Retrieve a customer by their ID
- Delete a customer

## Classes and Methods

### CustomerRewardsServiceImpl
- `addCustomer(Customer customer)`: Adds a new customer to the repository.
- `addProduct(Product product)`: Adds a new product to the repository.
- `updateProduct(int id, Product product)`: Updates an existing product in the repository.
- `updateCustomer(long id, Customer customer)`: Updates an existing customer in the repository.
- `getRewardsByCustomerId(Long customerId)`: Calculates the rewards for a customer based on their transactions in the last three months.
- `calculateCurrentMonthPurchaseReward(int customerId)`: Calculates the rewards for a customer's transactions in the current month.
- `getCustomerById(Long id)`: Retrieves a customer by their ID.
- `deleteCustomer(Long id)`: Deletes a customer from the repository.

### Helper Methods
- `getRewardsPerMonth(List<Product> lastMonthTransactions)`: Calculates the rewards for a list of transactions in a month.
- `calculateRewards(Product p)`: Calculates the rewards for a single transaction.
- `getDate(int d)`: Helper method to get a timestamp for a specified number of days in the past.

## Constants
- `Constants.daysInMonths`: Number of days in a month.
- `Constants.second`: Multiplier for the second month.
- `Constants.third`: Multiplier for the third month.
- `Constants.firstRewardLimit`: The lower limit for earning 1 point per dollar.
- `Constants.secondRewardLimit`: The upper limit for earning 1 point per dollar and the lower limit for earning 2 points per dollar.

## Usage
1. Add a new customer using `addCustomer(Customer customer)`.
2. Add a new product using `addProduct(Product product)`.
3. Update an existing customer using `updateCustomer(long id, Customer customer)`.
4. Update an existing product using `updateProduct(int id, Product product)`.
5. Calculate rewards for a customer based on their transactions in the last three months using `getRewardsByCustomerId(Long customerId)`.
6. Calculate rewards for a customer's transactions in the current month using `calculateCurrentMonthPurchaseReward(int customerId)`.
7. Retrieve a customer by their ID using `getCustomerById(Long id)`.
8. Delete a customer using `deleteCustomer(Long id)`.

## Example
1.Adding the customer
{
  "cust_name": "John Doe",
  "cust_email": "john@example.com",
  "phone_number": "9876543210"
}
2.Updating the customer
{
  "cust_name": "John Smith",
  "cust_email": "johnsmith@example.com",
  "phone_number": "9876543210"
}
3.Adding the product 
{
  "productType": "Electronics",
  "amount": 120.0,
  "customerId": 1
}
4.Getting the details of currentMonth reward
{
  "customerId": 1,
  "currentMonthRewardPoints": 90
}
5.Reward calculation for 3 months
{
  "customerId": 1,
  "lastMonthRewardPoints": 90,
  "lastSecondMonthRewardPoints": 120,
  "lastThirdMonthRewardPoints": 60,
  "totalRewards": 270
}






