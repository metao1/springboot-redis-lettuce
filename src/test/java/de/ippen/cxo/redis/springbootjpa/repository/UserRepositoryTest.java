package de.ippen.cxo.redis.springbootjpa.repository;

import de.ippen.cxo.redis.springbootjpa.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3"))
            .withExposedPorts(6379)
            .waitingFor(
                    Wait.forLogMessage(".*Ready to accept connections.*\\n", 1)
            );

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redis::getContainerIpAddress);
        registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
    }

    @BeforeAll
    public static void setupRedisClient() {
        redis.start();
    }

    @Test
    public void saveUser_isSuccessful() {
        User user = User.builder().id(1).gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
    }

    @Test
    public void getAllUsers_isSuccessful() {
        User user = User.builder().id(1).gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();
        log.info(users.toString());
    }

    @Test
    public void findAllUsersByGender_isSuccessful(){
        User user1 = User.builder().id(1L).gender(User.Gender.MALE).grade(20).name("Mehrdad").build();
        User user2 = User.builder().id(2L).gender(User.Gender.FEMALE).grade(21).name("Ziba").build();
        userRepository.save(user1);
        userRepository.save(user2);
        assertThat(userRepository.findAllByGender(User.Gender.MALE))
                .containsAll(List.of(user1));
    }


    @AfterAll
    public static void stop() {
        redis.stop();
    }
}