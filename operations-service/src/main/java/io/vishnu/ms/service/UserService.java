package io.vishnu.ms.service;

import io.vishnu.ms.model.User;

import java.util.List;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public interface UserService {
    User save(User user);
    List<User> findAllUsers();
    User getUserById(long userId);
}
