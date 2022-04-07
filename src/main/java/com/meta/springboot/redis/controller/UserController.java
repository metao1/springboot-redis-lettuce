package com.meta.springboot.redis.controller;

import lombok.RequiredArgsConstructor;

import com.meta.springboot.redis.model.User;
import com.meta.springboot.redis.model.exception.UserNotExist;
import com.meta.springboot.redis.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public Flux<User> getUsers() {
        return Flux.fromIterable(userRepository.findAll()).subscribeOn(Schedulers.boundedElastic());
    }

}
