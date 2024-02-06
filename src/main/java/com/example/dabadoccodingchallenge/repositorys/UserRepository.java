package com.example.dabadoccodingchallenge.repositorys;



import com.example.dabadoccodingchallenge.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username,String password);

}
