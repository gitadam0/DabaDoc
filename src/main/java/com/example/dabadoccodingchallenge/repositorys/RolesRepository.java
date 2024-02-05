package com.example.dabadoccodingchallenge.repositorys;



import com.example.dabadoccodingchallenge.entitys.Roles;
import com.example.dabadoccodingchallenge.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource
public interface RolesRepository extends JpaRepository<Roles, Long> {


}
