package dev.aaiyvan.customerservice.consumer;

import dev.aaiyvan.customerservice.mapper.CustomerMapper;
import dev.aaiyvan.customerservice.model.dto.CustomerConsumerRequest;
import dev.aaiyvan.customerservice.model.dto.CustomerRequest;
import dev.aaiyvan.customerservice.model.entity.Customer;
import dev.aaiyvan.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConsumer {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @KafkaListener(topics = "customer-saving-queue", groupId = "customer-service")
    public void consumeCustomer(
            final CustomerConsumerRequest request
    ) {
        customerService.createCustomer(customerMapper.toRequest(request));
    }

}
