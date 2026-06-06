package dev.abazure.bookingservice.client.handler;

import dev.abazure.bookingservice.client.exception.UpstreamServiceException;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public final class ClientErrorHandler {

    private ClientErrorHandler() {
    }

    public static void handle(
            HttpRequest request,
            ClientHttpResponse response
    ) throws IOException {

        int status = response.getStatusCode().value();

        if (status == 404) {
            throw new ResourceNotFoundException(
                    "Resource not found"
            );
        }
        throw new UpstreamServiceException(
                "Upstream error: " + status
        );
    }
}
