package dev.abazure.bookingservice.dto;

import lombok.Builder;

@Builder
public record BookingRequest(
        Long userId,
        Long eventId,
        Long ticketCount
) {
}
