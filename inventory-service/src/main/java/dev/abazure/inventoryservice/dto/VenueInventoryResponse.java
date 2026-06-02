package dev.abazure.inventoryservice.dto;

import lombok.Builder;

@Builder
public record VenueInventoryResponse(
        Long venueId,
        String venueName,
        Long totalCapacity
) {
}
