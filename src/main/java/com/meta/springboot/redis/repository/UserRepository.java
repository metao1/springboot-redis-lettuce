package com.meta.springboot.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.meta.springboot.redis.model.Gender;
import com.meta.springboot.redis.model.User;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findAllByGender(Gender gender);

    @Override
    Optional<User> findById(String s);
}
