package com.meta.springbootjpa.controller;

import com.meta.springbootjpa.model.User;
import com.meta.springbootjpa.model.exception.UserNotExist;
import com.meta.springbootjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public User getUser(@RequestParam("gender") User.Gender gender) {
        return userRepository.findAllByGender(gender).orElseThrow(UserNotExist::new);// just an example
    }

}
