package dev.aaiyvan.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Value("${spring.cloud.gateway.routes.discovery-server-url}")
    private String discoveryServerUrl;

    @Value("${spring.cloud.gateway.routes.customer-server-url}")
    private String customerServerUrl;

    @Bean
    RouteLocator routeLocator(
            RouteLocatorBuilder locatorBuilder
    ) {
        return locatorBuilder.routes()
                .route("customer-service", r -> r.path("/api/v1/customers/**")
                        .uri(customerServerUrl))
                .route("discovery-service", r -> r.path("/eureka/web")
                        .uri(discoveryServerUrl))
                .build();
    }

}
