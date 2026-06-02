package dev.abazure.inventoryservice.dto;

import dev.abazure.inventoryservice.exception.ErrorCode;

public record ErrorResponse(
        ErrorCode error,
        String message
) {
}
