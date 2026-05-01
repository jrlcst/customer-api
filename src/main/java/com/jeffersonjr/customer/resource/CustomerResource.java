package com.jeffersonjr.customer.resource;

import com.jeffersonjr.customer.dto.CustomerResponse;
import com.jeffersonjr.customer.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    @Path("/{id}")
    public CustomerResponse getCustomerById(@PathParam("id") String customerId) {
        return customerService.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + customerId));
    }
}