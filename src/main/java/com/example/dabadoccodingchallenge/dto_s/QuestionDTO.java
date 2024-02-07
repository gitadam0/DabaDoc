package com.example.dabadoccodingchallenge.dto_s;


import com.example.dabadoccodingchallenge.entitys.Answer;
import com.example.dabadoccodingchallenge.entitys.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;

    private String title;
    private String content;
    private String location;

    private String username;

    private List<AnswerDTO> answers = new ArrayList<>();
}