package io.vishnu.gw.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * spring-boot-microservice : io.vishnu.gw.fallback
 *
 * @author vishnu.g
 */
/*
 * This is general fallback provider of gateway. We can provide individual fallback provider for each microservice based
 * on route.
 */
@Component
public class GatewayFallbackProvider implements FallbackProvider {

    private static final Logger logger = LoggerFactory.getLogger(GatewayFallbackProvider.class);

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        logger.info("fallback provider called for service {}", route);
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return fallbackResponse();
        }
    }


    public ClientHttpResponse fallbackResponse() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
