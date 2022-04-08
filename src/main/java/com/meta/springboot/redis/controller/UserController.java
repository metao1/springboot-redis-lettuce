package com.meta.springboot.redis.controller;

import com.meta.springboot.redis.model.User;
import com.meta.springboot.redis.model.exception.UserNotExist;
import com.meta.springboot.redis.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public User getUserByName(@RequestParam("name") String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotExist("can't find user"));
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
