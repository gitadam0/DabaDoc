package com.example.dabadoccodingchallenge.repositorys;



import com.example.dabadoccodingchallenge.entitys.Roles;
import com.example.dabadoccodingchallenge.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RolesRepository extends JpaRepository<Roles, Long> {


}
