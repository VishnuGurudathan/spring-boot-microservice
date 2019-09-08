package io.vishnu.ms.service;

import io.vishnu.ms.model.User;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public interface UserService {
    /**
     *
     * @return
     */
    List<User> findAllUsers();

    /**
     * @param userId
     * @return
     */
    User getUserById(long userId);

    /**
     * Save user {@link User}
     * @param user
     * @return
     */
    User save(User user);

    void updateUser(User user);

    void removeUserById(long userId);
}
