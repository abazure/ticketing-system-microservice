package dev.abazure.inventoryservice.dto;

import dev.abazure.inventoryservice.entity.Venue;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record EventInventoryResponse(
        Long id,
        String event,
        Long capacity,
        Venue venue,
        BigInteger ticketPrice
) {
}
