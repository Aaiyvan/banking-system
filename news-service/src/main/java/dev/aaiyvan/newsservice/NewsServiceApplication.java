package dev.aaiyvan.newsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * This is the main entry point for the StorageService application.
 * It is annotated with @SpringBootApplication, indicating that it is a Spring Boot application,
 * also @EnableDiscoveryClient annotation to enable a DiscoveryClient implementation
 * and @EnableFeignClients that scans for interfaces that declare they are feign clients.
 * @author aaiyvan
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NewsServiceApplication {

    /**
     * The main method that starts the application.
     *
     * @param args command line arguments.
     */
    public static void main(
            final String[] args
    ) {
        SpringApplication.run(NewsServiceApplication.class, args);
    }

}
