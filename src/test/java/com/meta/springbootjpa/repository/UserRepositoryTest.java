package com.meta.springbootjpa.repository;

import com.meta.springboot.redis.model.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

import com.meta.springboot.redis.model.User;
import com.meta.springboot.redis.repository.UserRepository;

@Slf4j
class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_isSuccessful() {
        var user = User.builder().id("1").gender(Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
    }

    @Test
    public void getAllUsers_isSuccessful() {
        var user = User.builder().id("1").gender(Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
        var users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        log.info(users.toString());
    }

    @Test
    public void findAllUsersByGender_isSuccessful(){
        var user1 = User.builder().id("1").gender(Gender.MALE).grade(20).name("Mehrdad").build();
        var user2 = User.builder().id("2").gender(Gender.FEMALE).grade(21).name("Ziba").build();
        userRepository.save(user1);
        userRepository.save(user2);
        assertThat(userRepository.findAllByGender(Gender.MALE).get()).isEqualTo(user1);
    }


    @AfterAll
    public static void stop() {
        redis.stop();
    }
}
