package com.example.dabadoccodingchallenge.services.answerService;



import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;
    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public Answer getAnswerByID(long id) throws AppException {
        return answerRepository.findById(id).orElseThrow(()->new AppException("answer not found for "+id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Answer updateAnswer(long id, Answer newAnswer) {
        return null;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}
