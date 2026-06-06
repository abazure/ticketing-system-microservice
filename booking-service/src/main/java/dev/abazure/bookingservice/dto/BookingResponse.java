package dev.abazure.bookingservice.dto;

import lombok.Builder;

@Builder
public record BookingResponse(
        Long userId,
        Long eventId,
        Long totalCount,
        Long totalPrice
) {
}
