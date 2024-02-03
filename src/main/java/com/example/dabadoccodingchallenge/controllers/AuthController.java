package com.example.dabadoccodingchallenge.controllers;



import com.example.dabadoccodingchallenge.dto_s.UserDTO;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getUsername().toString());
        if (userRepository.existsUserByUsername(userDTO.getUsername())){
            return new ResponseEntity<String>("the user already exists", HttpStatus.I_AM_A_TEAPOT) ;
        }
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()) );
        user.setEnabled(true);
        userRepository.save(user);
        return new ResponseEntity<String>("the user is created", HttpStatus.ACCEPTED) ;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){

        Optional<User> user = userRepository.findUserByUsername(userDTO.getUsername());
        if(!user.isPresent()){
            return new ResponseEntity<String>("the user doesnt exists for the name "+userDTO.getUsername(), HttpStatus.NOT_FOUND);
        }

        if (passwordEncoder.matches(userDTO.getPassword(),user.get().getPassword())  ){
                return new ResponseEntity<String>("connected succsefully", HttpStatus.OK) ;
        }

        return new ResponseEntity<String>("wrong password", HttpStatus.UNAUTHORIZED) ;
    }

}
