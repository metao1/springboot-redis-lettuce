package com.metao.springboot.redis.config;

import java.time.Duration;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.resource.ClientResources;
import org.springframework.util.StringUtils;

@Setter
@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.redis")
public class RedisManagerConfig {

    private String host;

    private int port;

    private String password;

    private int database;

    private String clientName;

    private int connectionTimeoutMillis;

    private int numTestsPerEvictionRun;

    private int minIdle;

    private int maxWaitMillis;

    private int commandTimeoutMillis;

    private int maxTotal;

    private int maxIdle;

    @Bean
    public ClientOptions clientOptions() {
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .socketOptions(
                        SocketOptions.builder().connectTimeout(Duration.ofMillis(connectionTimeoutMillis)).build())
                .autoReconnect(true)
                .build();
    }

    @Bean
    public LettuceConnectionFactory lettucePoolConfig(
            LettucePoolingClientConfiguration lettucePoolingClientConfiguration,
            RedisStandaloneConfiguration redisStandaloneConfiguration) {

        var lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                lettucePoolingClientConfiguration);
        lettuceConnectionFactory.setShareNativeConnection(false);

        return lettuceConnectionFactory;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        if (password != null && !password.isBlank()) {
            redisStandaloneConfiguration.setPassword(password);
        }
        return redisStandaloneConfiguration;
    }

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
        var clientConfig = LettuceClientConfiguration.builder().useSsl().build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    @Bean
    public LettucePoolingClientConfiguration lettucePoolConfig(ClientResources clientResources, ClientOptions options) {
        var redisPoolConfig = new GenericObjectPoolConfig<>();

        redisPoolConfig.setMaxTotal(maxTotal);
        redisPoolConfig.setMaxIdle(maxIdle);
        redisPoolConfig.setMinIdle(minIdle);
        redisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        redisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        return LettucePoolingClientConfiguration
                .builder()
                .poolConfig(redisPoolConfig)
                .clientOptions(options)
                .clientName(clientName)
                .clientResources(clientResources)
                .shutdownTimeout(Duration.ZERO)
                .commandTimeout(Duration.ofMillis(commandTimeoutMillis)).build();
    }

}
