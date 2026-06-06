package dev.abazure.bookingservice.dto;

import dev.abazure.bookingservice.exception.ErrorCode;

public record ErrorResponse(
        ErrorCode error,
        String message
) {
}
