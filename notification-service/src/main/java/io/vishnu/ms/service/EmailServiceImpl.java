package io.vishnu.ms.service;

import io.vishnu.ms.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * spring-boot-microservice : io.vishnu.ms.service
 *
 * @author vishnu.g
 */
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(UserDto input) {
        try {

//            Mail newMail = new Mail();
//            newMail.setTo(input.getUsername());
//            newMail.setSubject("TestSubject");
//            newMail.setText("TestText");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("vishnu.g@experionglobal.com");
            message.setSubject("Testing subject");
            message.setText("Testing mail");

//            mailRepository.save(newMail);
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
