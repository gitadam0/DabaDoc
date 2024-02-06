package com.example.dabadoccodingchallenge.repositorys;

import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.entitys.Question;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionRepository extends JpaRepository<Question, Long> {


}