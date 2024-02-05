package com.example.dabadoccodingchallenge.dto_s;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsDTO {


    private String username;
    private String password;

}
