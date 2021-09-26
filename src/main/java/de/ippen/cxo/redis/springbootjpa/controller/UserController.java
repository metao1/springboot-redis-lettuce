package de.ippen.cxo.redis.springbootjpa.controller;

import de.ippen.cxo.redis.springbootjpa.model.User;
import de.ippen.cxo.redis.springbootjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public User getUser() {
        return userRepository.findAllByGender(User.Gender.MALE).iterator().next();
    }
}
