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
    public RouterFunction<ServerResponse> bookingServiceRoute() {
        return GatewayRouterFunctions.route("booking-service")
                .route(RequestPredicates.path("/api/v1/booking/**"),
                        HandlerFunctions.http()
                )
                .before(uri("http://booking-service:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "booking-service-cb",
                        URI.create("forward:/fallback/booking")))
                .build();
    }
}