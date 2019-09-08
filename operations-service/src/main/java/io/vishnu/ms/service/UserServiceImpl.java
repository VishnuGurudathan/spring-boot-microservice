package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDetailsDto;
import io.vishnu.ms.exception.UserNotFoundException;
import io.vishnu.ms.model.User;
import io.vishnu.ms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

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

    @Autowired
    private KafkaTemplate<String, ?> kafkaTemplate;

    private static final String TOPIC = "TOPIC_USER_CREATED";

    @Override
    public User save(User user) {
        User createdUser = userRepository.save(user);
        UserDetailsDto detailsDto = new UserDetailsDto(createdUser.getId(), createdUser.getName(), createdUser.getPhoneNumber(), createdUser.getEmail());
        send(detailsDto);
          // kafkaTemplate.send(TOPIC, createdUser);
        return createdUser;
    }

    // Move to message service.
    private void send(Object payload) {

         kafkaTemplate
                .send(MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.TOPIC, TOPIC).build());


    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        logger.info("total number of users : {}", userList.size());
        return userList;
    }

    @Override
    public User getUserById(long userId) {
        System.out.println("userId = [" + userId + "]");
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUserById(long userId) {
        userRepository.deleteById(userId);
    }

}
