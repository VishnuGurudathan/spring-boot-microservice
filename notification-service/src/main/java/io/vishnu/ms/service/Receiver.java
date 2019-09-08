package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
@Component
public class Receiver {
    //private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(2);

    @Autowired
    private EmailService emailService;

    public CountDownLatch getLatch() {
        return latch;
    }



    @KafkaListener(topics = "TOPIC_USER_CREATED")
    public void receiveFoo(UserDetailsDto userDto) {
        System.out.println("userDto = [" + userDto + "]");
        emailService.sendSimpleMessage(userDto);
        latch.countDown();
    }
}
