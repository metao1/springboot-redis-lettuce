package com.meta.springboot.redis.controller;

import lombok.RequiredArgsConstructor;

import com.meta.springboot.jpa.model.Gender;
import com.meta.springboot.jpa.model.User;
import com.meta.springboot.jpa.model.exception.UserNotExist;
import com.meta.springboot.jpa.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public User getUser(@RequestParam("gender") Gender gender) {
        return userRepository.findAllByGender(gender).orElseThrow(UserNotExist::new);// just an example
    }

}
