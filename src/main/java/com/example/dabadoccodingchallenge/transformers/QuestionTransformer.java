package com.example.dabadoccodingchallenge.transformers;

import com.example.dabadoccodingchallenge.dto_s.QuestionDTO;
import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public  class QuestionTransformer {
   
    public static Question toEntity(QuestionDTO questionDTO) {
        Question newQuestion=new Question();
        newQuestion.setId(questionDTO.getId());
        newQuestion.setTitle(questionDTO.getTitle());
        newQuestion.setContent(questionDTO.getContent());
        newQuestion.setLocation(questionDTO.getLocation());

        newQuestion.setAnswers(AnswerTransformer.toEntityList(questionDTO.getAnswers()));

        newQuestion.setUser(new User(questionDTO.getUsername()));

        return newQuestion;
    }

    public static List<Question> toEntityList(List<QuestionDTO> questionDTOs){
            return questionDTOs.stream().map(QuestionTransformer::toEntity).
                    collect(Collectors.toList());

    }


    public static QuestionDTO toDto(Question question){
        QuestionDTO newQuestionDTO=new QuestionDTO();
        newQuestionDTO.setId(question.getId());
        newQuestionDTO.setTitle(question.getTitle());
        newQuestionDTO.setContent(question.getContent());
        newQuestionDTO.setLocation(question.getLocation());
        newQuestionDTO.setUsername(question.getUser().getUsername() );
        newQuestionDTO.setAnswers(AnswerTransformer.toDtoList(question.getAnswers()) );
        return newQuestionDTO;
    }
    public static List<QuestionDTO> toDtoList(List<Question> questions){

        return questions.stream().map(QuestionTransformer::toDto).
                collect(Collectors.toList());
    }
}
