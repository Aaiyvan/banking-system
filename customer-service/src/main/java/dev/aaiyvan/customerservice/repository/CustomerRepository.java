package dev.aaiyvan.customerservice.repository;

import dev.aaiyvan.customerservice.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
