package com.meta.springbootjpa.service;

import com.meta.springbootjpa.model.User;
import com.meta.springbootjpa.repository.BaseRepositoryTest;
import com.meta.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import static com.meta.springbootjpa.service.LikeOperation.USER;

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