package com.jeffersonjr.customer.service;

import com.jeffersonjr.customer.dto.CustomerResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    private final Map<String, CustomerResponse> customers = Map.of(
            "cus-001", new CustomerResponse("cus-001", "Maria Silva", "12345678900", "ACTIVE"),
            "cus-002", new CustomerResponse("cus-002", "Joao Souza", "98765432100", "BLOCKED"),
            "cus-003", new CustomerResponse("cus-003", "Ana Lima", "11122233344", "INACTIVE")
    );

    public Optional<CustomerResponse> findById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }
}