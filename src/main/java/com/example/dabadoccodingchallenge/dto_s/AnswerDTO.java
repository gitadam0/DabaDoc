package com.example.dabadoccodingchallenge.dto_s;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private Long id;
    private String answer;
    
    private Long question_id;
    private String user_id;
}