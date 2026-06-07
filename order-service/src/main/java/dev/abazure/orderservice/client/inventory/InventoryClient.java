package dev.abazure.orderservice.client.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class InventoryClient {
    private final RestClient restClient;

    public void updateInventory(Long eventId, Long ticketCount){
         restClient.put()
                .uri("/events/{eventId}/capacity/{capacity}", eventId, ticketCount)
                .retrieve()
                .toBodilessEntity();
    }
}