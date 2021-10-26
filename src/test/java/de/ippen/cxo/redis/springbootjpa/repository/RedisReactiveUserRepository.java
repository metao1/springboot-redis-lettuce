package de.ippen.cxo.redis.springbootjpa.repository;

import de.ippen.cxo.redis.springbootjpa.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisReactiveUserRepository {

    @Autowired
    private ReactiveRedisTemplate<String, User> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    private ReactiveValueOperations<String, User> reactiveValueOps;

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

    @BeforeEach
    public void beforeEach() {
        reactiveValueOps = redisTemplate.opsForValue();
    }

    @Test
    public void givenUser_whenSet_thenSet() {
        Mono<Boolean> result = reactiveValueOps.set("123", new User(123, "Bill", User.Gender.MALE, 20));

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    @AfterAll
    public static void destroy() {
        redis.stop();
    }
}
