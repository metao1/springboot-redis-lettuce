package com.meta.springbootjpa.service;

import com.meta.springbootjpa.model.User;
import com.meta.springbootjpa.repository.BaseRepositoryTest;
import com.meta.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static com.meta.springbootjpa.service.LikeOperation.USER;

@SpringBootTest
class LikeServiceTest {

    @Autowired
    LikeService likeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate<String, User> userRedisTemplate;

    @Test
    void likeUser() {
        User user1 = User.builder().id("1").gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        User user2 = User.builder().id("2").gender(User.Gender.FEMALE).grade(22).name("Ziba").build();
        userRedisTemplate.opsForHash().put(USER, user1.getId(), user1);
        userRedisTemplate.opsForHash().put(USER, user2.getId(), user2);
        likeService.likeUser(user1, user2);
    }
}