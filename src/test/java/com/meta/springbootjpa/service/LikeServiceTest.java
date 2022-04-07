package com.meta.springbootjpa.service;


import com.meta.springboot.redis.model.Gender;
import com.meta.springboot.redis.model.User;
import com.meta.springboot.redis.repository.UserRepository;
import com.meta.springboot.redis.service.LikeService;
import com.meta.springbootjpa.repository.BaseRepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

class LikeServiceTest extends BaseRepositoryTest {

    @Autowired
    LikeService likeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate userRedisTemplate;

    @Test
    void likeUser() {
        User user1 = User.builder().id("1").gender(Gender.MALE).grade(20).name("Mehrdad").build();
        User user2 = User.builder().id("2").gender(Gender.FEMALE).grade(22).name("Ziba").build();
        likeService.likeUser(user1, user2);
    }
}
