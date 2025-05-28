package com.customer.retailer.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class ProductTest {

    @Test
    public void testValidData() {
        Product product = new Product(1, "Electronics", 299.99, Timestamp.valueOf("2023-01-01 10:10:10"), 12345L);
        assertEquals(1, product.getProductId());
        assertEquals("Electronics", product.getProductType());
        assertEquals(299.99, product.getAmount());
        assertEquals(Timestamp.valueOf("2023-01-01 10:10:10"), product.getTransactionDate());
        assertEquals(12345L, product.getCust_id());
    }

    @Test
    public void testNullValues() {
        Product product = new Product();
        product.setProductType(null);
        product.setTransactionDate(null);
        assertEquals(null, product.getProductType());
        assertEquals(null, product.getTransactionDate());
    }

    @Test
    public void testEmptyStringValues() {
        Product product = new Product();
        product.setProductType("");
        assertEquals("", product.getProductType());
    }

    @Test
    public void testMaxMinValues() {
        Product product = new Product();
        product.setProductId(Integer.MAX_VALUE);
        product.setAmount(Double.MAX_VALUE);
        product.setCust_id(Long.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, product.getProductId());
        assertEquals(Double.MAX_VALUE, product.getAmount());
        assertEquals(Long.MAX_VALUE, product.getCust_id());

        product.setProductId(Integer.MIN_VALUE);
        product.setAmount(Double.MIN_VALUE);
        product.setCust_id(Long.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, product.getProductId());
        assertEquals(Double.MIN_VALUE, product.getAmount());
        assertEquals(Long.MIN_VALUE, product.getCust_id());
    }

    @Test
    public void testNegativeValues() {
        Product product = new Product();
        product.setAmount(-100.0);
        product.setCust_id(-12345L);
        assertEquals(-100.0, product.getAmount());
        assertEquals(-12345L, product.getCust_id());
    }

    @Test
    public void testInvalidTimestamp() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> {
            product.setTransactionDate(Timestamp.valueOf("invalid-timestamp"));
        });
    }
}
