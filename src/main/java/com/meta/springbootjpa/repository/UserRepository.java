package com.meta.springbootjpa.repository;

import com.meta.springbootjpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findAllByGender(User.Gender gender);

    @Override
    Optional<User> findById(String s);
}
