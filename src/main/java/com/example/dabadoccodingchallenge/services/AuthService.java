package com.example.dabadoccodingchallenge.services;


import com.example.dabadoccodingchallenge.dto_s.UserCredentialsDTO;
import com.example.dabadoccodingchallenge.dto_s.UserDTO;
import com.example.dabadoccodingchallenge.entitys.Roles;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.RolesRepository;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service

public class AuthService {
    //private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    public AuthService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.rolesRepository=rolesRepository;
    }
    public ResponseEntity<UserDTO> login(UserCredentialsDTO userCredentialsDTO) throws AppException {

        Optional<User> user = userRepository.findUserByUsername(userCredentialsDTO.getUsername());
        if(!user.isPresent()){
            throw new AppException("the user doesnt exists for the name "+userCredentialsDTO.getUsername(), HttpStatus.NOT_FOUND);
        }

        if (passwordEncoder.matches(userCredentialsDTO.getPassword(),user.get().getPassword())  ){
            UserDTO userDTO=new UserDTO(
                    userCredentialsDTO.getUsername(),
                    userCredentialsDTO.getPassword(),
                    ""
            );
                return new ResponseEntity<>(userDTO, HttpStatus.OK) ;
        }

        throw new AppException("wrong password", HttpStatus.UNAUTHORIZED) ;
    }

    public ResponseEntity<String> register(UserDTO userDTO) throws AppException {

        System.out.println(userDTO.getUsername().toString());
        if (userRepository.existsUserByUsername(userDTO.getUsername())){
            throw new AppException("the user already exists", HttpStatus.UNAUTHORIZED) ;
        }

        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()) );
        user.setEnabled(true);

        Roles roles=new Roles();
        roles.setUser(userRepository.save(user));
        roles.setRole("ROLE_ADMIN");

        rolesRepository.save(roles);

        return new ResponseEntity<String>("the user is created", HttpStatus.ACCEPTED) ;


    }

}
