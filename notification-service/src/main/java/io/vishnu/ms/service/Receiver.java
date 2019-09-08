package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public class Receiver {
    //private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(2);

    @Autowired
    private EmailService emailService;

    public CountDownLatch getLatch() {
        return latch;
    }



    @KafkaListener(topics = "TOPIC_USER_CREATED")
    public void receiveFoo(UserDto userDto) {
        System.out.println("userDto = [" + userDto + "]");
        emailService.sendSimpleMessage(userDto);
        latch.countDown();
    }
}
