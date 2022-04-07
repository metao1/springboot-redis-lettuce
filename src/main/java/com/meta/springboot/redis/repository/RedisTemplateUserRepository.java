package com.meta.springboot.redis.repository;

import com.meta.springboot.jpa.model.User;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStreamOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public abstract class RedisTemplateUserRepository implements UserRepository {

    private final ReactiveRedisTemplate<String, User> redisTemplate;
    private final ReactiveStreamOperations<String, User, User> reactiveStreamOps;
    private ReactiveValueOperations<String, User> reactiveValueOps;

    public RedisTemplateUserRepository(ReactiveRedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.reactiveValueOps = redisTemplate.opsForValue();
        this.reactiveStreamOps = redisTemplate.opsForStream();
    }

}
