package com.meta.springboot.redis.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not exist")
public class UserNotExist extends RuntimeException {

    private final String name;

    public UserNotExist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
