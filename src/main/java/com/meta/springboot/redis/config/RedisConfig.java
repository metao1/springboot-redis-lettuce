package com.meta.springboot.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meta.springboot.jpa.model.User;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }

    @Bean
    public Jackson2JsonRedisSerializer<User> getValueSerializer(ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }

    @Bean
    public StringRedisSerializer getKeySerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveRedisTemplate(StringRedisSerializer keySerializer,
                                                                     Jackson2JsonRedisSerializer<User> valueSerializer,
                                                                     ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, User> context =
                builder
                        .hashKey(keySerializer)
                        .hashValue(valueSerializer)
                        .key(keySerializer)
                        .value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public RedisTemplate<String, User> messagePackRedisTemplate(StringRedisSerializer keySerializer,
                                                                Jackson2JsonRedisSerializer<User> valueSerializer,
                                                                RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public ObjectMapper messagePackObjectMapper() {
        return new ObjectMapper(new MessagePackFactory())
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}