package com.example.dabadoccodingchallenge.controllers;


import com.example.dabadoccodingchallenge.dto_s.QuestionDTO;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.services.questionService.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin("http://localhost:4200")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(
            QuestionService questionService
    ){
        this.questionService=questionService;
    }
    @GetMapping()
    public List<QuestionDTO> getAllQuestions(){
        return questionService.getQuestions() ;
    }
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) throws AppException {
        return questionService.getQuestionByID(id) ;
    }

    @PostMapping()
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question) ;
    }


}
