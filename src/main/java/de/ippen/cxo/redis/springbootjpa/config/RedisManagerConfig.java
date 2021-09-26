package de.ippen.cxo.redis.springbootjpa.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;

@Configuration
public class RedisManagerConfig {


    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.database:0}")
    private int database;

    @Value("${spring.redis.client-name:default-client-name}")
    private String clientName;

    @Value("${spring.redis.connection-timeout:60000}")
    private int connectionTimeoutMillis;

    @Value("${spring.redis.num-tests-per-eviction-run:3}")
    private int numTestsPerEvictionRun;

    @Value("${spring.redis.lettuce.pool.min-idle:5}")
    private int minIdle;

    @Value("${spring.redis.lettuce.pool.max-wait-millis:60000}")
    private int maxWaitMillis;


    @Value("${spring.redis.lettuce.pool.command-timeout-millis:2000}")
    private int commandTimeoutMillis;


    @Value("${spring.redis.lettuce.pool.max-total:100}")
    private int maxTotal;

    @Value("${spring.redis.lettuce.pool.max-idle:10}")
    private int maxIdle;

    @Bean
    public ClientOptions clientOptions() {
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .socketOptions(SocketOptions.builder().connectTimeout(Duration.ofMillis(connectionTimeoutMillis)).build())
                .autoReconnect(true)
                .build();
    }

    @Bean
    public LettuceConnectionFactory lettucePoolConfig(LettucePoolingClientConfiguration lettucePoolingClientConfiguration, RedisStandaloneConfiguration redisStandaloneConfiguration) {

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
        lettuceConnectionFactory.setShareNativeConnection(false);

        return lettuceConnectionFactory;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return redisStandaloneConfiguration;
    }

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl().build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    @Bean
    public LettucePoolingClientConfiguration lettucePoolConfig(ClientResources clientResources, ClientOptions options) {
        GenericObjectPoolConfig<Boolean> redisPoolConfig = new GenericObjectPoolConfig<>();

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
