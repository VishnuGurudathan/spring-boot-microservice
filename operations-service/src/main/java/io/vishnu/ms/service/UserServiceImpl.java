package io.vishnu.ms.service;

import io.vishnu.ms.model.User;
import io.vishnu.ms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
@Component
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        logger.info("total number of users : {}", userList.size());
        return userList;
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return  null;
    }
}
