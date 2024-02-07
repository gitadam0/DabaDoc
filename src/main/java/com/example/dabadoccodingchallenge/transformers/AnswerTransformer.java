package com.example.dabadoccodingchallenge.transformers;

import com.example.dabadoccodingchallenge.dto_s.AnswerDTO;
import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class AnswerTransformer {
    private static UserRepository userRepository;

    public static void setUserRepository(UserRepository userRepository) {
        AnswerTransformer.userRepository = userRepository;
    }
    public static Answer toEntity(AnswerDTO answerDTO)  {
        Answer newAnswer=new Answer();
        newAnswer.setId(answerDTO.getId());
        newAnswer.setAnswer(answerDTO.getAnswer());
        newAnswer.setQuestion(new Question(answerDTO.getQuestion_id()) );
        newAnswer.setUser(new User(answerDTO.getUser_id()));
        /* try {
            newAnswer.setUser(
                    userRepository.findUserByUsername(answerDTO.getUser_id())
                            .orElseThrow(()->new AppException("user not dound for id "+answerDTO.getUser_id(), HttpStatus.NOT_FOUND))
            );
        } catch (AppException e) {
            throw new RuntimeException(e);
        }*/
        return newAnswer;
    }

    public static List<Answer> toEntityList(List<AnswerDTO> answerDTOs) {
        /*return answerDTOs.stream().map((i)->toEntity(i)).
                collect(Collectors.toList());*/
            return answerDTOs.stream().map(AnswerTransformer::toEntity).
                    collect(Collectors.toList());

    }


    public static AnswerDTO toDto(Answer answer){
        AnswerDTO newAnswerDTO=new AnswerDTO();
        newAnswerDTO.setId(answer.getId());
        newAnswerDTO.setAnswer(answer.getAnswer());
        newAnswerDTO.setQuestion_id(answer.getQuestion().getId() );
        newAnswerDTO.setUser_id(answer.getUser().getUsername());
        return newAnswerDTO;
    }
    public static List<AnswerDTO> toDtoList(List<Answer> answers){

        return answers.stream().map(AnswerTransformer::toDto).
                collect(Collectors.toList());
    }
}
