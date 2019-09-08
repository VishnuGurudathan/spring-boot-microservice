package io.vishnu.ms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * spring-boot-microservice : io.vishnu.ms.exception
 *
 * @author vishnu.g
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User does not exist");
    }
}
