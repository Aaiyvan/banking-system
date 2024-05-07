package dev.aaiyvan.customerservice.controller;


import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerResponse;
import dev.aaiyvan.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/info/{customerId}")
    public ResponseEntity<CustomerResponse> getInfoCustomer(
            @PathVariable final UUID customerId
    ) {
        return ResponseEntity.ok(customerService.getInfoCustomer(customerId));
    }

    @PatchMapping("{customerId}/avatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @PathVariable UUID customerId) {
        customerService.uploadAvatar(customerId, file);
        return ResponseEntity.ok("Avatar of customer with id: " + customerId + " was uploaded successfully.");
    }

    @DeleteMapping("/{customerId}/avatar")
    public ResponseEntity<String> deleteAvatar(
            @PathVariable UUID customerId
    ) {
        customerService.deleteAvatar(customerId);
        return ResponseEntity.ok("Avatar of customer with id: " + customerId + " was deleted successfully.");
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody final CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody final CustomerRequest customerRequest,
            @PathVariable final UUID customerId
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(customerRequest, customerId));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable final UUID customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer with id: " + customerId + " was deleted");
    }

}
