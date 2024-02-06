package com.example.dabadoccodingchallenge.controllers;


import com.example.dabadoccodingchallenge.config.UserAuthProvider;
import com.example.dabadoccodingchallenge.dto_s.UserDTO;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.QuestionRepository;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import com.example.dabadoccodingchallenge.services.userService.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder ;

    public UserController(
            UserService userService,
            PasswordEncoder passwordEncoder
    ){
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }
    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getUsers() ;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws AppException {
        System.out.println(userService.getUserByID(id).getQuestions());
        return userService.getUserByID(id) ;
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws AppException {
         userService.deleteUser(id) ;
    }
    @PostMapping()
    public User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(
                user.getPassword()));
        return userService.createUser(user) ;
    }



}
