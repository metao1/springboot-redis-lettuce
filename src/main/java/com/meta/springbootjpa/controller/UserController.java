package com.meta.springbootjpa.controller;

import com.meta.springbootjpa.model.User;
import com.meta.springbootjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
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
