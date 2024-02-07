
package com.example.dabadoccodingchallenge.services.answerService;


import com.example.dabadoccodingchallenge.dto_s.AnswerDTO;
import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.exceptions.AppException;

import java.util.List;

public interface AnswerService {

    public List<AnswerDTO> getAnswers();

    public void deleteAnswer(Long id);
    public Answer getAnswerByID(long id) throws AppException;

    public Answer updateAnswer(long id,Answer newAnswer) ;


    public Answer createAnswer(AnswerDTO catalogue) throws AppException;
}
