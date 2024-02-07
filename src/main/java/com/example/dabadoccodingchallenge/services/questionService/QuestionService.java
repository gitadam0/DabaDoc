
package com.example.dabadoccodingchallenge.services.questionService;


import com.example.dabadoccodingchallenge.dto_s.QuestionDTO;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.exceptions.AppException;

import java.util.List;

public interface QuestionService {

    public List<QuestionDTO> getQuestions();

    public void deleteQuestion(Long id);
    public Question getQuestionByID(long id) throws AppException;

    public Question updateQuestion(long id,Question newQuestion) ;

    public Question createQuestion(Question catalogue);
}
