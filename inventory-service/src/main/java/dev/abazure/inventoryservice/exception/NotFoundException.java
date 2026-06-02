package dev.abazure.inventoryservice.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final ErrorCode code;

    public NotFoundException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
