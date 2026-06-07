package dev.abazure.orderservice.client.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class LoggingInterceptor
        implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {

        long start = System.currentTimeMillis();

        ClientHttpResponse response =
                execution.execute(request, body);

        log.info(
                "outbound method={} uri={} status={} durationMs={}",
                request.getMethod(),
                request.getURI(),
                response.getStatusCode().value(),
                System.currentTimeMillis() - start
        );

        return response;
    }
}

