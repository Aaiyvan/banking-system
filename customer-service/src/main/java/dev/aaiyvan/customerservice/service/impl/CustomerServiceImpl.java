package dev.aaiyvan.customerservice.service.impl;

import dev.aaiyvan.customerservice.exception.CustomerNotFoundException;
import dev.aaiyvan.customerservice.exception.InvalidArgumentException;
import dev.aaiyvan.customerservice.mapper.CustomerMapper;
import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.model.entity.Customer;
import dev.aaiyvan.customerservice.repository.CustomerRepository;
import dev.aaiyvan.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer getByCustomerId(
            final UUID customerId
    ) {
        return customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerMapper.toResponse(customerRepository.findAll());
    }

    @Override
    public CustomerResponse getInfoCustomer(
            final UUID customerId
    ) {
        return customerMapper.toResponse(getByCustomerId(customerId));
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(
            final CustomerRequest customerRequest
    ) {
        Customer customer = customerMapper.toCustomer(customerRequest);

        if (customer == null) {
            throw new InvalidArgumentException("Customer or accountId cannot be null");
        }

        customerRepository.save(customer);
        log.info("Saving customer with id: {}", customer.getId());

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(
            final CustomerRequest customerRequest,
            final UUID customerId
    ) {
        Customer existingCustomer = getByCustomerId(customerId);
        Customer updatedCustomer = customerMapper.toCustomer(customerRequest);
        updatedCustomer.setId(existingCustomer.getId());

        customerRepository.save(updatedCustomer);
        log.info("Updating customer with id: {}", updatedCustomer.getId());

        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(
            final UUID customerId
    ) {
        customerRepository.deleteById(customerId);
    }

}
