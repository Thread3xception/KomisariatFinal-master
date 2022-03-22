package com.esley.apigateway.configuration;

import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Consumer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.*;

@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator gateway(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routeSpec -> routeSpec
                        .path("/driver/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("driverCB").setFallbackUri("/fallback"))
                                .retry(getRetryConfig()))
                        .uri("lb://driver-service"))
                .route(routeSpec -> routeSpec
                        .path("/ticket/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("ticketCB").setFallbackUri("/fallback"))
                                .retry(getRetryConfig()))
                        .uri("lb://ticket-service"))
                .route(routeSpec -> routeSpec
                        .path("/offense/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("offenseCB").setFallbackUri("/fallback"))
                                .retry(getRetryConfig()))
                        .uri("lb://offense-service"))
                .route(routeSpec -> routeSpec
                        .path("/ticketoffense/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("ticketoffenseCB").setFallbackUri("/fallback"))
                                .retry(getRetryConfig()))
                        .uri("lb://ticketoffense-service"))
                .build();
    }

    private Consumer<RetryGatewayFilterFactory.RetryConfig> getRetryConfig() {
        return config -> config
                .setRetries(3)
                .setBackoff(Duration.ofMillis(300), null, 3, false)
                .setMethods(GET)
                .setStatuses(BAD_GATEWAY, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR);
    }
}
