package dev.abazure.orderservice.client.exception;

public class UpstreamServiceException
        extends RuntimeException {

    public UpstreamServiceException(
            String message
    ) {
        super(message);
    }
}

