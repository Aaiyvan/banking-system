package dev.aaiyvan.customerservice.service.impl;

import dev.aaiyvan.customerservice.client.StorageServiceClient;
import dev.aaiyvan.customerservice.exception.CustomerNotFoundException;
import dev.aaiyvan.customerservice.mapper.CustomerMapper;
import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.model.entity.Customer;
import dev.aaiyvan.customerservice.model.payload.FileUploadResponse;
import dev.aaiyvan.customerservice.repository.CustomerRepository;
import dev.aaiyvan.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final StorageServiceClient storageServiceClient;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Value("${minio.s3.bucket.profile-bucket}")
    private String CUSTOMER_PROFILE;

    @Value("${minio.s3.default.avatar}")
    private String DEFAULT_AVATAR;

    @Override
    public Customer getCustomer(
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
        return customerMapper.toResponse(getCustomer(customerId));
    }

    @Override
    public CustomerResponse createCustomer(
            final CustomerRequest customerRequest
    ) {
        Customer customer = customerMapper.toCustomer(customerRequest);

        customerRepository.save(customer);
        log.info("Saving customer with id: {}", customer.getId());

        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(
            final CustomerRequest customerRequest,
            final UUID customerId
    ) {
        Customer existingCustomer = getCustomer(customerId);
        Customer updatedCustomer = customerMapper.toCustomer(customerRequest);
        updatedCustomer.setId(existingCustomer.getId());
        updatedCustomer.setAvatar(existingCustomer.getAvatar());

        customerRepository.save(updatedCustomer);
        log.info("Updating customer with id: {}", updatedCustomer.getId());

        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(
            final UUID customerId
    ) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void uploadAvatar(
            final UUID customerId,
            final MultipartFile file
    ) {
        Customer customer = getCustomer(customerId);
        FileUploadResponse[] fileUploadResponses = storageServiceClient.uploadAvatar(
                CUSTOMER_PROFILE,
                String.valueOf(customerId),
                file).getBody();
        log.info("Avatar was uploaded.");
        assert fileUploadResponses != null;
        customer.setAvatar(fileUploadResponses[0].url());
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteAvatar(
            final UUID customerId
    ) {

        Customer customer = getCustomer(customerId);
        String[] info = storageServiceClient.getInfoAvatar(customer.getAvatar()).getBody();
        assert info != null;
        storageServiceClient.deleteAvatar(info[0], info[1]);
        customer.setAvatar(DEFAULT_AVATAR);
        customerRepository.saveAndFlush(customer);
    }

}
