package com.metao.springboot.redis.service;

import com.metao.springboot.redis.model.User;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final RedisTemplate<String, User> userRedisTemplate;

    public void likeUser(User srcUser, User dstUser) {
        userRedisTemplate.execute(LikeOperation.of(srcUser.getId(), dstUser.getId(), 1));
        Object o = userRedisTemplate.opsForHash().getOperations();
        log.info(String.valueOf(o));
    }
}
