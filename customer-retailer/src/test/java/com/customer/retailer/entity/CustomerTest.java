package com.customer.retailer.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testValidData() {
        customer.setCust_id(1L);
        customer.setCust_name("John Doe");
        customer.setCust_email("john.doe@example.com");
        customer.setPhone_number(1234567890L);

        assertEquals(1L, customer.getCust_id());
        assertEquals("John Doe", customer.getCust_name());
        assertEquals("john.doe@example.com", customer.getCust_email());
        assertEquals(1234567890L, customer.getPhone_number());
    }

    @Test
    public void testNullValues() {
        customer.setCust_name(null);
        customer.setCust_email(null);

        assertEquals(null, customer.getCust_name());
        assertEquals(null, customer.getCust_email());
    }

    @Test
    public void testEmptyStrings() {
        customer.setCust_name("");
        customer.setCust_email("");

        assertEquals("", customer.getCust_name());
        assertEquals("", customer.getCust_email());
    }

    @Test
    public void testMaxLongValues() {
        customer.setCust_id(Long.MAX_VALUE);
        customer.setPhone_number(Long.MAX_VALUE);

        assertEquals(Long.MAX_VALUE, customer.getCust_id());
        assertEquals(Long.MAX_VALUE, customer.getPhone_number());
    }

    @Test
    public void testMinLongValues() {
        customer.setCust_id(Long.MIN_VALUE);
        customer.setPhone_number(Long.MIN_VALUE);

        assertEquals(Long.MIN_VALUE, customer.getCust_id());
        assertEquals(Long.MIN_VALUE, customer.getPhone_number());
    }

    @Test
    public void testNegativePhoneNumber() {
        customer.setPhone_number(-1234567890L);

        assertEquals(-1234567890L, customer.getPhone_number());
    }

    @Test
    public void testToString() {
        customer.setCust_id(1L);
        customer.setCust_name("John Doe");
        customer.setCust_email("john.doe@example.com");
        customer.setPhone_number(1234567890L);

        String expected = "Customer [cust_id=1, cust_name=John Doe, cust_email=john.doe@example.com, phone_number=1234567890]";
        assertEquals(expected, customer.toString());
    }
}
