package com.metao.springboot.redis.repository;

import java.util.Optional;

import com.metao.springboot.redis.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByName(String name);
}
