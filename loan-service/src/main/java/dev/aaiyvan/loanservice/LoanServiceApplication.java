package dev.aaiyvan.loanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoanServiceApplication {

    public static void main(
            final String[] args
    ) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

}
