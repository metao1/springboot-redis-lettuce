package com.meta.springboot.redis.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.meta.springboot.jpa.service.LikeOperation.USER;

import com.meta.springboot.jpa.model.User;
import com.meta.springboot.jpa.repository.UserRepository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class LikeService {

    private final RedisTemplate<String, User> userRedisTemplate;

    public void likeUser(User srcUser, User dstUser) {
        userRedisTemplate.execute(LikeOperation.of(srcUser.getId(), dstUser.getId(), 1));
        Object o = userRedisTemplate.opsForHash().getOperations();
        log.info(String.valueOf(o));
    }
}
