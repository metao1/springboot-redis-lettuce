package com.meta.springbootjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

import com.meta.springboot.jpa.model.User;
import com.meta.springboot.jpa.repository.UserRepository;

@Slf4j
class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_isSuccessful() {
        User user = User.builder().id("1").gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
    }

    @Test
    public void getAllUsers_isSuccessful() {
        User user = User.builder().id("1").gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        log.info(users.toString());
    }

    @Test
    public void findAllUsersByGender_isSuccessful(){
        User user1 = User.builder().id("1").gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        User user2 = User.builder().id("2").gender(User.Gender.FEMALE).grade(21).name("Ziba").build();
        userRepository.save(user1);
        userRepository.save(user2);
        assertThat(userRepository.findAllByGender(User.Gender.MALE).get()).isEqualTo(user1);
    }


    @AfterAll
    public static void stop() {
        redis.stop();
    }
}