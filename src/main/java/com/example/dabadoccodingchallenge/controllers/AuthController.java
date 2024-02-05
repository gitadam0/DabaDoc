package com.example.dabadoccodingchallenge.controllers;



import com.example.dabadoccodingchallenge.config.UserAuthProvider;
import com.example.dabadoccodingchallenge.dto_s.UserCredentialsDTO;
import com.example.dabadoccodingchallenge.dto_s.UserDTO;
import com.example.dabadoccodingchallenge.entitys.Roles;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.RolesRepository;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import com.example.dabadoccodingchallenge.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {
    //private AuthenticationManager authenticationManager;
    //private PasswordEncoder passwordEncoder;

    private AuthService authService;
    private UserAuthProvider userAuthProvider;

    public AuthController(AuthService authService,UserAuthProvider userAuthProvider){
        this.authService=authService;
        this.userAuthProvider=userAuthProvider;
    }
    @GetMapping("/get")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("goooooooooooooooooooooooodddddd", HttpStatus.OK) ;
    }

    /*@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getUsername().toString());
        if (userRepository.existsUserByUsername(userDTO.getUsername())){
            return new ResponseEntity<String>("the user already exists", HttpStatus.I_AM_A_TEAPOT) ;
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
    }*/



    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserCredentialsDTO UserCredentialsDTO) throws AppException {

        UserDTO userDTO=authService.login(UserCredentialsDTO).getBody();

        userDTO.setToken(userAuthProvider.createToken(userDTO));

        return new ResponseEntity<>(userDTO, HttpStatus.OK) ;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) throws AppException {
        authService.register(userDTO);
        return new ResponseEntity<String>("registered in", HttpStatus.OK) ;
    }

}
