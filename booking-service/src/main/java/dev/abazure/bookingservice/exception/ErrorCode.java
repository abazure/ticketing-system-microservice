package dev.abazure.bookingservice.exception;

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

    // CUSTOMER
    CUSTOMER_NOT_FOUND("Customer Not Found"),

    // EVENT
    INSUFFICIENT_STOCK("Insufficient Stock");

    private final String message;

}
