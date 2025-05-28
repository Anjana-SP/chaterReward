package com.customer.retailer.service;


import com.customer.retailer.entity.Customer;
import com.customer.retailer.entity.Product;
import com.customer.retailer.model.Rewards;
import com.customer.retailer.repository.CustomerRepository;
import com.customer.retailer.repository.ProductRepository;
import com.customer.retailer.serviceImpl.CustomerRewardsServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerRewardsServiceImplTest {

    @InjectMocks
    private CustomerRewardsServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = service.addCustomer(customer);

        verify(customerRepository).save(customer);
        assertEquals(customer, result);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product result = service.addProduct(product);

        verify(productRepository).save(product);
        assertEquals(product, result);
    }

    @Test
    void testGetRewardsByCustomerId() {
        Long customerId = 1L;
        Product product = new Product();
        product.setAmount(120.0);
        when(productRepository.findAllByCustomerId(eq(customerId), any(), any()))
                .thenReturn(List.of(product));

        Rewards rewards = service.getRewardsByCustomerId(customerId);

        assertEquals(customerId, rewards.getCustomerId());
        assertTrue(rewards.getTotalRewards() >= 0);
    }

    @Test
    void testCalculateCurrentMonthPurchaseReward() {
        int customerId = 1;
        Product product = new Product();
        product.setAmount(150.0);
        when(productRepository.findAllByCustomerId(eq(customerId), any(), any()))
                .thenReturn(List.of(product));

        Long reward = service.calculateCurrentMonthPurchaseReward(customerId);

        assertTrue(reward > 0);
    }

    @Test
    void testUpdateProduct() {
        int id = 1;
        Product existing = new Product();
        existing.setAmount(100.0);
        existing.setProductType("Old");

        Product updated = new Product();
        updated.setAmount(200.0);
        updated.setProductType("New");

        when(productRepository.getProductById(id)).thenReturn(existing);
        when(productRepository.save(any())).thenReturn(existing);

        Product result = service.updateProduct(id, updated);

        assertEquals(200.0, result.getAmount());
        assertEquals("New", result.getProductType());
    }

    @Test
    void testUpdateCustomer() {
        long id = 1L;
        Customer existing = new Customer();
        existing.setCust_name("Old");
        existing.setCust_email("old@mail.com");
        existing.setPhone_number(123);

        Customer updated = new Customer();
        updated.setCust_name("New");
        updated.setCust_email("new@mail.com");
        updated.setPhone_number(456);

        when(customerRepository.findCustomerById(id)).thenReturn(existing);
        when(customerRepository.save(any())).thenReturn(existing);

        Customer result = service.updateCustomer(id, updated);

        assertEquals("New", result.getCust_name());
        assertEquals("new@mail.com", result.getCust_email());
    }

    @Test
    void testGetCustomerById() {
        long id = 1L;
        Customer customer = new Customer();
        when(customerRepository.findCustomerById(id)).thenReturn(customer);

        Customer result = service.getCustomerById(id);

        assertEquals(customer, result);
    }

    @Test
    void testDeleteCustomer() {
        long id = 1L;
        doNothing().when(customerRepository).deleteById(id);

        service.deleteCustomer(id);

        verify(customerRepository).deleteById(id);
    }
}
