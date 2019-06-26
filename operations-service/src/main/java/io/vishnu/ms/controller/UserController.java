package io.vishnu.ms.controller;

import io.vishnu.ms.dto.UserDetailsDto;
import io.vishnu.ms.model.User;
import io.vishnu.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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

    @RequestMapping("/")
    public String defaultRoute() {
        return "Hello from Operations Service running at port: " + env.getProperty("local.server.port");
    }
    @GetMapping("/all-users")
    public Mono getallUsers()throws Exception {
        long now = System.currentTimeMillis();
        logger.info("service '" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                "' completed after " + (System.currentTimeMillis() - now) + " ms");
        return Mono.just(Arrays.asList("Vishnu", "Siva"));
    }

    @GetMapping("/{id}")
    public Mono getUser(@PathVariable("id") long userId) {
        System.out.println("i am user : " + userId);
        User userEntity = userService.getUserById(userId);
        return Mono.just(userEntity);
    }

    @PostMapping(path="/save", consumes = "application/json")
    public Mono saveUser(@RequestBody UserDetailsDto user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(user.getId())
//                .toUri();
        // TODO : Need to create a response builder and send all response through that.
        return Mono.just(userService.save(userEntity));
    }

    @PutMapping("/update/{id}")
    public Mono updateUser(@PathVariable("id") long userId, @RequestBody UserDetailsDto user) {
        return Mono.just(user);
    }

    @PatchMapping("/patch/{id}")
    public Mono patchUser(@PathVariable("id") long userId, @RequestBody UserDetailsDto user) {
        return Mono.just(user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono deleteUser(@PathVariable("id") long userId) {
        return Mono.empty();
    }
}
