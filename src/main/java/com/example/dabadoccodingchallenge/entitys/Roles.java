package com.example.dabadoccodingchallenge.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Roles {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;
}
