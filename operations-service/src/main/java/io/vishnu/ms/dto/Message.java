package io.vishnu.ms.dto;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * spring-boot-microservice : io.vishnu.ms.dto
 *
 * @author vishnu.g
 */
public class Message {
    private String requestId;
    private String event;
    private String [] channels;
    private JsonNode payload;
}
