package dev.aaiyvan.customerservice.service;

import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.model.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getByCustomerId(UUID customerId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getInfoCustomer(UUID customerId);

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomer(CustomerRequest customerRequest, UUID customerId);

    void deleteCustomer(UUID customerId);

}
