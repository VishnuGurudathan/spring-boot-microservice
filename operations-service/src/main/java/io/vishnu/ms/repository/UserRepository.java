package io.vishnu.ms.repository;

import io.vishnu.ms.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * spring-boot-microservice : io.vishnu.ms.repository
 *
 * @author vishnu.g
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
