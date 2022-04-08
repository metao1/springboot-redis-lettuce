package com.metao.springboot.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseRedisIntegrationTest {

    private static final GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3"))
            .withExposedPorts(6379)
            .waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\n", 1));

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redis::getContainerIpAddress);
        registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
    }

    @BeforeAll
    public static void setupRedisClient() {
        redis.start();
    }

    @AfterAll
    public static void destroy() {
        redis.stop();
    }

}
