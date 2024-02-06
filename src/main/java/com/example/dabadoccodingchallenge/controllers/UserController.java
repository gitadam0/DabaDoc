package com.example.dabadoccodingchallenge.controllers;


import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.repositorys.QuestionRepository;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private QuestionRepository questionRepository;

    public UserController(
            UserRepository userRepository,
            QuestionRepository questionRepository
    ){
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
    }
    @GetMapping()
    public List<User> get(){
        return userRepository.findAll() ;
    }
    @GetMapping("questions")
    public List<Question> getq(){
        return questionRepository.findAll() ;
    }
    @PostMapping("questions")
    public Question saveq(@RequestBody Question question){
        System.out.println(question.toString());
        return questionRepository.save(question) ;
    }
}
