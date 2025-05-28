package com.customer.retailer.controller;

import com.customer.retailer.controller.CustomerRewardsController;
import com.customer.retailer.entity.Customer;
import com.customer.retailer.entity.Product;
import com.customer.retailer.exception.CustomerNotFoundException;
import com.customer.retailer.model.Rewards;
import com.customer.retailer.repository.CustomerRepository;
import com.customer.retailer.serviceImpl.CustomerRewardsServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRewardsControllerTest {

    @InjectMocks
    private CustomerRewardsController controller;

    @Mock
    private CustomerRewardsServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer_Success() {
        Customer customer = new Customer();
        when(service.addCustomer(customer)).thenReturn(customer);

        ResponseEntity<?> response = controller.addCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testAddCustomer_Exception() {
        Customer customer = new Customer();
        when(service.addCustomer(customer)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controller.addCustomer(customer);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error", response.getBody());
    }

    @Test
    void testAddProduct_Success() {
        Product product = new Product();
        when(service.addProduct(product)).thenReturn(product);

        ResponseEntity<?> response = controller.addProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testAddProduct_Exception() {
        Product product = new Product();
        when(service.addProduct(product)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controller.addProduct(product);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error", response.getBody());
    }

    @Test
    void testUpdateProduct_Success() {
        Product product = new Product();
        when(service.updateProduct(1, product)).thenReturn(product);

        ResponseEntity<String> response = controller.updateProduct(1, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody());
    }

    @Test
    void testUpdateProduct_Failure() {
        Product product = new Product();
        when(service.updateProduct(1, product)).thenReturn(null);

        ResponseEntity<String> response = controller.updateProduct(1, product);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to update", response.getBody());
    }

    @Test
    void testUpdateCustomer_Success() {
        Customer customer = new Customer();
        when(service.updateCustomer(1, customer)).thenReturn(customer);

        ResponseEntity<String> response = controller.updateCustomer(1, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody());
    }

    @Test
    void testUpdateCustomer_Failure() {
        Customer customer = new Customer();
        when(service.updateCustomer(1, customer)).thenReturn(null);

        ResponseEntity<String> response = controller.updateCustomer(1, customer);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to update", response.getBody());
    }

    @Test
    void testGetCurrentMonthPurchaseReward() {
        when(service.calculateCurrentMonthPurchaseReward(1)).thenReturn(100L);

        Long result = controller.getCurrentMonthPurchaseReward(1);

        assertEquals(100L, result);
    }

    @Test
    void testGetRewardsByCustomerId_Success() {
        Long customerId = 1L;
        Customer customer = new Customer();
        Rewards rewards = new Rewards();

        when(customerRepository.findCustomerById(customerId)).thenReturn(customer);
        when(service.getRewardsByCustomerId(customerId)).thenReturn(rewards);

        ResponseEntity<Rewards> response = controller.getRewardsByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rewards, response.getBody());
    }

    @Test
    void testGetRewardsByCustomerId_NotFound() {
        Long customerId = 1L;
        when(customerRepository.findCustomerById(customerId)).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> {
            controller.getRewardsByCustomerId(customerId);
        });
    }

    @Test
    void testDeleteCustomer_Success() {
        Long id = 1L;
        Customer customer = new Customer();
        when(service.getCustomerById(id)).thenReturn(customer);
        doNothing().when(service).deleteCustomer(id);

        ResponseEntity<String> response = controller.deleteCustomer(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }

    @Test
    void testDeleteCustomer_NotFound() {
        Long id = 1L;
        when(service.getCustomerById(id)).thenReturn(null);

        ResponseEntity<String> response = controller.deleteCustomer(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }
}
