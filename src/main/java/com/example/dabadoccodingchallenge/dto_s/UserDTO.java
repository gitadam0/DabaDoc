package com.example.dabadoccodingchallenge.dto_s;

import com.example.dabadoccodingchallenge.entitys.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {


    private String username;
    private String password;

}
