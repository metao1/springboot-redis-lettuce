package de.ippen.cxo.redis.springbootjpa.controller;

import de.ippen.cxo.redis.springbootjpa.model.User;
import de.ippen.cxo.redis.springbootjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public void saveUser() {
        User user = User.builder().id(1).gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
    }
}
