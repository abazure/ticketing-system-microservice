package dev.abazure.inventoryservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    //COMMON ERRORS
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    BAD_REQUEST("Bad Request"),
    METHOD_NOT_ALLOWED("Method Not Allowed"),
    NOT_FOUND("Not Found"),

    // VENUE
    VENUE_NOT_FOUND("Venue Not Found"),

    // EVENT
    EVENT_NOT_FOUND("Event Not Found");

    private final String message;

}
