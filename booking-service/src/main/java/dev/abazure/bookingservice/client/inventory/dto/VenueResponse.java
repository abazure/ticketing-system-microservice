package dev.abazure.bookingservice.client.inventory.dto;

import lombok.Builder;

@Builder
public record VenueResponse(
        Long id,
        String name,
        String address,
        Long totalCapacity
) {
}
