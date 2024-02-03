package com.example.dabadoccodingchallenge.repositorys;



import com.example.dabadoccodingchallenge.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username,String password);

}
