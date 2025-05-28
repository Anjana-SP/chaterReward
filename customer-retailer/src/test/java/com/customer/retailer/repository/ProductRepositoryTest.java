package com.customer.retailer.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.customer.retailer.entity.Product;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1, "Electronics", 100.0, new Timestamp(System.currentTimeMillis()), 1L);
    }

    @Test
    public void testGetProductDetails() {
        when(productRepository.getProductDetails(1)).thenReturn(product);
        Product result = productRepository.getProductDetails(1);
        assertNotNull(result);
        assertEquals(1, result.getProductId());
        assertEquals("Electronics", result.getProductType());
    }

    @Test
    public void testFindAllByCustomerId() {
        Timestamp from = Timestamp.valueOf("2023-01-01 00:00:00");
        Timestamp to = Timestamp.valueOf("2023-12-31 23:59:59");
        List<Product> products = Arrays.asList(
            new Product(1, "Electronics", 100.0, new Timestamp(System.currentTimeMillis()), 1L),
            new Product(2, "Books", 50.0, new Timestamp(System.currentTimeMillis()), 1L)
        );

        when(productRepository.findAllByCustomerId(1L, from, to)).thenReturn(products);
        List<Product> result = productRepository.findAllByCustomerId(1L, from, to);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        when(productRepository.getProductById(1)).thenReturn(product);
        Product result = productRepository.getProductById(1);
        assertNotNull(result);
        assertEquals(1, result.getProductId());
    }
}
