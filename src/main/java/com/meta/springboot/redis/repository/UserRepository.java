package com.meta.springboot.redis.repository;

import java.util.Optional;

import com.meta.springboot.redis.model.Gender;
import com.meta.springboot.redis.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findAllByGender(Gender gender);
}
