package io.vishnu.ms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vishnu.ms.dto.UserDetailsDto;
import io.vishnu.ms.model.User;
import io.vishnu.ms.service.UserService;

/**
 * spring-boot-microservice : io.vishnu.gw.controller
 *
 * @author vishnu.g
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @RequestMapping("/status")
    public String defaultRoute() {
        return "Hello from Operations Service running at port: " + env.getProperty("local.server.port");
    }
    @GetMapping("/all-users")
    public List<User> getallUsers() {
        long now = System.currentTimeMillis();
        // common format
        logger.info("service '{}' completed after {} ms", Thread.currentThread().getStackTrace()[1].getMethodName(), (System.currentTimeMillis() - now));
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long userId) {
        System.err.println("i am user : " + userId);
        //return this.userService.getUserById(userId);
        User userEntity = userService.getUserById(userId);
        System.out.println("user = [" + userEntity + "]");
        return userEntity;
    }

    @PostMapping(path="/", consumes = "application/json")
    public User saveUser(@RequestBody UserDetailsDto user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(user.getId())
//                .toUri();
        // TODO : Need to create a response builder and send all response through that.
        return userService.save(userEntity);
    }
/*
    @PutMapping("/update/{id}")
    public Mono updateUser(@PathVariable("id") long userId, @RequestBody UserDetailsDto user) {
        return Mono.just(user);
        Student studentToUpdate = repository.findById(id).orElseThrow(StudentNotFoundException::new);

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setYear(student.getYear());

        return repository.save(studentToUpdate);
    }

    @PatchMapping("/patch/{id}")
    public Mono patchUser(@PathVariable("id") long userId, @RequestBody UserDetailsDto user) {
        return Mono.just(user);
    }
*/
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long userId) {
        userService.removeUserById(userId);
    }
}
