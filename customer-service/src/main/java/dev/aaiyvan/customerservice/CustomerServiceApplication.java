package dev.aaiyvan.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    public static void main(
            final String[] args
    ) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
