package com.meta.springbootjpa.repository;

import com.meta.springbootjpa.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class RedisReactiveUserRepository extends BaseRepositoryTest {

    @Autowired
    private ReactiveRedisTemplate<String, User> redisTemplate;

    private ReactiveValueOperations<String, User> reactiveValueOps;

    @BeforeEach
    public void beforeEach() {
        reactiveValueOps = redisTemplate.opsForValue();
    }

    @Test
    public void givenUser_whenSet_thenSet() {
        Mono<Boolean> result = reactiveValueOps.set("123", new User("123", "Bill", User.Gender.MALE, 20));
        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    @AfterAll
    public static void destroy() {
        redis.stop();
    }
}
