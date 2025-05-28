package com.customer.retailer.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.customer.retailer.exception.CustomerNotFoundException;




	class CustomerNotFoundExceptionTest {

	    @Test
	    void testExceptionMessage() {
	        String message = "Customer with ID 123 not found.";
	        CustomerNotFoundException exception = new CustomerNotFoundException(message);

	        assertEquals(message, exception.getMessage());
	    }

	    @Test
	    void testExceptionIsRuntimeException() {
	        CustomerNotFoundException exception = new CustomerNotFoundException("Test");
	        assertTrue(exception instanceof RuntimeException);
	    }
	}
