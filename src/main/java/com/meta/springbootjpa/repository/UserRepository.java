package com.meta.springbootjpa.repository;

import com.meta.springbootjpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Iterable<User> findAllByGender(User.Gender gender);

}
