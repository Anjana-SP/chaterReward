package com.customer.retailer.repository;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.retailer.entity.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1L, "John Doe", "john.doe@example.com", 1234567890L);
    }

    @Test
    public void testFindCustomerById() {
        when(customerRepository.findCustomerById(anyLong())).thenReturn(customer);

        Customer foundCustomer = customerRepository.findCustomerById(1L);

        assertEquals(customer.getCust_id(), foundCustomer.getCust_id());
        assertEquals(customer.getCust_name(), foundCustomer.getCust_name());
        assertEquals(customer.getCust_email(), foundCustomer.getCust_email());
        assertEquals(customer.getPhone_number(), foundCustomer.getPhone_number());
    }

    @Test
    public void testFindCustomerById_NotFound() {
        when(customerRepository.findCustomerById(anyLong())).thenReturn(null);

        Customer foundCustomer = customerRepository.findCustomerById(2L);

        assertEquals(null, foundCustomer);
    }
}
