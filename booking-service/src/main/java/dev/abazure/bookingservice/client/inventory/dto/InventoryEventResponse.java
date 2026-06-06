package dev.abazure.bookingservice.client.inventory.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record InventoryEventResponse(
        Long eventId,
        String event,
        Long capacity,
        VenueResponse venue,
        BigInteger ticketPrice

) {
}
