package dev.abazure.bookingservice.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    private final ErrorCode code;

    public ConflictException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
