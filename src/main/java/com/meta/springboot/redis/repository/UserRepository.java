package com.meta.springboot.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.meta.springboot.jpa.model.Gender;
import com.meta.springboot.jpa.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findAllByGender(Gender gender);

    @Override
    Optional<User> findById(String s);
}
