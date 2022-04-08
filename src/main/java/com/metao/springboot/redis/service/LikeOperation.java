package com.metao.springboot.redis.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.metao.springboot.redis.model.LikeEvent;
import com.metao.springboot.redis.model.User;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class LikeOperation implements SessionCallback<List<Object>> {

    public static final String USER = "user";
    public static final String LIKE = "like";
    private final String fromUserId;
    private final String toUserId;
    private final long amount;

    @Override
    public <K, V> List<Object> execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
        var operations = (RedisTemplate<String, User>) redisOperations;
        var hashOperation = operations.opsForHash();

        var fromUser = (User) hashOperation.get(USER, fromUserId);
        var toUser = (User) hashOperation.get(USER, toUserId);

        if (Objects.nonNull(fromUser) && Objects.nonNull(toUser)) {
            try {
                operations.multi();
                LikeEvent likeEvent = LikeEvent.builder().id(UUID.randomUUID().clockSequence())
                        .count(amount)
                        .srcUserId(fromUserId)
                        .dstUserId(toUserId)
                        .build();
                hashOperation.put(LIKE, fromUserId, likeEvent);
                hashOperation.put(LIKE, toUserId, likeEvent);

                return operations.exec();
            } catch (Exception ex) {
                operations.discard();
            }
        }

        return Collections.emptyList();
    }
}
