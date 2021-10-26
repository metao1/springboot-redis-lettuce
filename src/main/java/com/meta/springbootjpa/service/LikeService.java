package com.meta.springbootjpa.service;

import com.meta.springbootjpa.model.User;
import com.meta.springbootjpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.meta.springbootjpa.service.LikeOperation.USER;


@Slf4j
@Service
@AllArgsConstructor
public class LikeService {

    private final RedisTemplate<String, User> userRedisTemplate;

    public void likeUser(User srcUser, User dstUser) {
        userRedisTemplate.execute(LikeOperation.of(srcUser.getId(), dstUser.getId(), 1));
        Object o = userRedisTemplate.opsForHash().getOperations();
        log.info(String.valueOf(o));
    }
}
