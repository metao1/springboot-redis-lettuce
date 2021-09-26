package de.ippen.cxo.redis.springbootjpa.service;

import de.ippen.cxo.redis.springbootjpa.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {

    private final RedisTemplate<String, User> userRedisTemplate;

    public void likeUser(User srcUser, User dstUser) {
        userRedisTemplate.execute(LikeOperation.of(srcUser.getId(), dstUser.getId(), 1));
    }
}
