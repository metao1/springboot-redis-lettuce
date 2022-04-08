package com.metao.springboot.redis.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.metao.springboot.redis.BaseRedisIntegrationTest;
import com.metao.springboot.redis.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisReactiveUserRepositoryTest extends BaseRedisIntegrationTest {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    private ValueOperations<String, User> valueOps;

    @BeforeEach
    public void beforeEach() {
        valueOps = redisTemplate.opsForValue();
    }

    @Test
    public void givenUser_whenSet_thenSet() {
        valueOps.set("123", new User("123", "Bill", 20));
        assertNotNull(valueOps.get("123"));
        assertEquals("Bill", valueOps.get("123").getName());
    }
}
