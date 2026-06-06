package dev.abazure.bookingservice.client.inventory;

import dev.abazure.bookingservice.client.inventory.dto.InventoryEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class InventoryClient {
    private final RestClient restClient;

    public InventoryEventResponse getEvent(Long eventId) {
        return restClient.get()
                .uri("/events/{eventId}", eventId)
                .retrieve()
                .body(InventoryEventResponse.class);
    }

}
