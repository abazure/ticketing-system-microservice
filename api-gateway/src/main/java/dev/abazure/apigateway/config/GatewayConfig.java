package dev.abazure.apigateway.config;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;

@Configuration
public class GatewayConfig {

    @Bean
    RouterFunction<ServerResponse> inventoryRoute() {
        return GatewayRouterFunctions.route("inventory-service")
                .route(
                        RequestPredicates.path("/api/v1/inventories/**"),
                        HandlerFunctions.http()
                )
                .before(uri("http://inventory-service:8080"))
                .filter(
                        CircuitBreakerFilterFunctions.circuitBreaker(
                                "inventory-service-cb",
                                URI.create("forward:/fallback/inventory")
                        )
                )
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> bookingRoute() {
        return GatewayRouterFunctions.route("booking-service")
                .route(
                        RequestPredicates.path("/api/v1/bookings/**"),
                        HandlerFunctions.http()
                )
                .before(uri("http://booking-service:8080"))
                .filter(
                        CircuitBreakerFilterFunctions.circuitBreaker(
                                "booking-service-cb",
                                URI.create("forward:/fallback/booking")
                        )
                )
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> inventoryDocsRoute() {
        return GatewayRouterFunctions.route("inventory-docs")
                .route(
                        RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                        HandlerFunctions.http()
                )
                .before(uri("http://inventory-service:8080"))
                .before(setPath("/v3/api-docs"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> bookingDocsRoute() {
        return GatewayRouterFunctions.route("booking-docs")
                .route(
                        RequestPredicates.path("/docs/bookingservice/v3/api-docs"),
                        HandlerFunctions.http()
                )
                .before(uri("http://booking-service:8080"))
                .before(setPath("/v3/api-docs"))
                .build();
    }
}