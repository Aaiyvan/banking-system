package dev.aaiyvan.customerservice.service;

import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.model.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomer(UUID customerId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getInfoCustomer(UUID customerId);

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse updateCustomer(CustomerRequest customerRequest, UUID customerId);

    void deleteCustomer(UUID customerId);

    void uploadAvatar(UUID id, MultipartFile file);

    void deleteAvatar(UUID id);
}
