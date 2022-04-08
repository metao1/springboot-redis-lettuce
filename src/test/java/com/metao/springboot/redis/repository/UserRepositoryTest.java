package com.metao.springboot.redis.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.metao.springboot.redis.BaseRedisIntegrationTest;
import com.metao.springboot.redis.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class UserRepositoryTest extends BaseRedisIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_isSuccessful() {
        var user = User.builder().id("1").timeout(20).name("Mehrdad").build();
        userRepository.save(user);
    }

    @Test
    public void getAllUsers_isSuccessful() {
        var user = User.builder().id("1").timeout(20).name("Mehrdad").build();
        userRepository.save(user);
        var users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        log.info(users.toString());
    }

    @Test
    public void findAllUsersByGender_isSuccessful() {
        var user1 = User.builder().id("1").timeout(20).name("Mehrdad").build();
        var user2 = User.builder().id("2").timeout(21).name("Ziba").build();
        userRepository.save(user1);
        userRepository.save(user2);
        assertThat(userRepository.findByName("Mehrdad")).isNotNull();
    }

}
