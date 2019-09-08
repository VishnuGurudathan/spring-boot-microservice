package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDetailsDto;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public interface EmailService {
    void sendSimpleMessage(UserDetailsDto input);
}
