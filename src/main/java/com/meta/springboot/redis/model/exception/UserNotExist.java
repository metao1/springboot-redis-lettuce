package com.meta.springboot.redis.model.exception;

import com.meta.springboot.jpa.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotExist extends RuntimeException {

    private User user;

    public UserNotExist() {
        log.error("user does not exist");
    }

    public UserNotExist(User user) {
        this.user = user;
    }
}
