package com.meta.springbootjpa.service;

import static com.meta.springboot.jpa.service.LikeOperation.USER;

import com.meta.springboot.jpa.model.User;
import com.meta.springboot.jpa.repository.UserRepository;
import com.meta.springboot.jpa.service.LikeService;
import com.meta.springbootjpa.repository.BaseRepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;

class LikeServiceTest extends BaseRepositoryTest {

    @Autowired
    LikeService likeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate userRedisTemplate;

    @Test
    void likeUser() {
        User user1 = User.builder().id("1").gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        User user2 = User.builder().id("2").gender(User.Gender.FEMALE).grade(22).name("Ziba").build();        
        likeService.likeUser(user1, user2);
    }
}