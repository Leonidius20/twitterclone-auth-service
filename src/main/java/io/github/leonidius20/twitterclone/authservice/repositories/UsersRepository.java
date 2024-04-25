package io.github.leonidius20.twitterclone.authservice.repositories;

import io.github.leonidius20.twitterclone.authservice.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Integer> {

    // List<User> findAllByUsernameStartingWith(String usernameQuery);

    User findByUsername(String username);

}
