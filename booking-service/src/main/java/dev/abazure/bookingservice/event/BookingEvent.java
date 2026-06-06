package dev.abazure.bookingservice.event;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record BookingEvent(
        Long userId,
        Long eventId,
        Long ticketCount,
        BigInteger totalPrice
) {
}
