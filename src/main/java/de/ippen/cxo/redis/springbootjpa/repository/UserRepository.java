package de.ippen.cxo.redis.springbootjpa.repository;

import de.ippen.cxo.redis.springbootjpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Iterable<User> findAllByGender(User.Gender gender);

}
