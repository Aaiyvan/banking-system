package dev.aaiyvan.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * This is the main application class for the Api Gateway.
 * It is annotated with @SpringBootApplication to indicate that it is a Spring Boot application.
 * It is also annotated with @EnableDiscoveryClient to indicate that it is a discovery client.
 *
 * @Author aaiyvan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    /**
     * This is the main method which serves as an entry point for the application.
     * It uses the SpringApplication.run() method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(
            final String[] args
    ) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
