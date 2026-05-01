package com.jeffersonjr.customer.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();

    @Test
    void shouldFindActiveCustomer() {
        var customer = customerService.findById("cus-001");

        assertTrue(customer.isPresent());
        assertEquals("ACTIVE", customer.get().status());
        assertEquals("12345678900", customer.get().document());
    }

    @Test
    void shouldFindBlockedCustomer() {
        var customer = customerService.findById("cus-002");

        assertTrue(customer.isPresent());
        assertEquals("BLOCKED", customer.get().status());
    }

    @Test
    void shouldReturnEmptyForUnknownCustomer() {
        assertFalse(customerService.findById("cus-404").isPresent());
    }
}