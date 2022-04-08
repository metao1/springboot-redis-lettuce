package com.metao.springboot.redis.service;

import com.metao.springboot.redis.BaseRedisIntegrationTest;
import com.metao.springboot.redis.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LikeServiceTest extends BaseRedisIntegrationTest {

    @Autowired
    LikeService likeService;

    @Test
    void likeUser() {
        var user1 = User.builder().id("1").timeout(10).name("Mehrdad").build();
        var user2 = User.builder().id("2").timeout(10).name("Ziba").build();
        likeService.likeUser(user1, user2);
    }
}
