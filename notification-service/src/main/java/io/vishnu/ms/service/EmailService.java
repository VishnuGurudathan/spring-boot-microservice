package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDto;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public interface EmailService {
    void sendSimpleMessage(UserDto input);
}
