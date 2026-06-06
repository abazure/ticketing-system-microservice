package dev.abazure.bookingservice.config;

import dev.abazure.bookingservice.client.handler.ClientErrorHandler;
import dev.abazure.bookingservice.client.interceptor.LoggingInterceptor;
import dev.abazure.bookingservice.client.inventory.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .requestInterceptor(new LoggingInterceptor())
                .defaultStatusHandler(HttpStatusCode::isError, ClientErrorHandler::handle)
                .build();
        return new InventoryClient(restClient);
    }
}
