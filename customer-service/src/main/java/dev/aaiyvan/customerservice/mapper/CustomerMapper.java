package dev.aaiyvan.customerservice.mapper;

import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponse(List<Customer> customers);

}
