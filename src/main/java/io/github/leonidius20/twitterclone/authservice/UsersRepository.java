package io.github.leonidius20.twitterclone.authservice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {

    List<User> findAllByUsernameStartingWith(String usernameQuery);

}
