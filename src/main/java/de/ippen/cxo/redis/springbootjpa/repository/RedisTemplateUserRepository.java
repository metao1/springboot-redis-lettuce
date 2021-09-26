package de.ippen.cxo.redis.springbootjpa.repository;

import de.ippen.cxo.redis.springbootjpa.model.User;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStreamOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class RedisTemplateUserRepository implements UserRepository {

    private final ReactiveRedisTemplate<String, User> redisTemplate;
    private final ReactiveStreamOperations<String, User, User> reactiveStreamOps;
    private ReactiveValueOperations<String, User> reactiveValueOps;

    public RedisTemplateUserRepository(ReactiveRedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.reactiveValueOps = redisTemplate.opsForValue();
        this.reactiveStreamOps =  redisTemplate.opsForStream();
    }

    @Override
    public Iterable<User> findAllByGender(User.Gender gender) {
        return null;
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
