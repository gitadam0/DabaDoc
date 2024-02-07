package com.example.dabadoccodingchallenge.services.questionService;



import com.example.dabadoccodingchallenge.dto_s.QuestionDTO;
import com.example.dabadoccodingchallenge.entitys.Question;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.QuestionRepository;
import com.example.dabadoccodingchallenge.transformers.QuestionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        return QuestionTransformer.toDtoList(questionRepository.findAll());
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    @Override
    public Question getQuestionByID(long id) throws AppException {
        return questionRepository.findById(id).orElseThrow(()->new AppException("question not found for "+id, HttpStatus.NOT_FOUND));
    }
    @Override
    public Question updateQuestion(long id, Question newQuestion) {
        return null;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
}
