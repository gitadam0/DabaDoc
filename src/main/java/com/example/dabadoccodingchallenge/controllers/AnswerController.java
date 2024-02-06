package com.example.dabadoccodingchallenge.controllers;


import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.services.answerService.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private AnswerService answerService;

    public AnswerController(
            AnswerService answerService
    ){
        this.answerService=answerService;
    }
    @GetMapping()
    public List<Answer> getAllAnswers(){
        return answerService.getAnswers() ;
    }
    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) throws AppException {
        return answerService.getAnswerByID(id) ;
    }

    @PostMapping()
    public Answer createAnswer(@RequestBody Answer answer){
        return answerService.createAnswer(answer) ;
    }


}
