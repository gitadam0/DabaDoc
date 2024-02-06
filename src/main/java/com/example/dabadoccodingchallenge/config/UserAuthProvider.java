package com.example.dabadoccodingchallenge.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dabadoccodingchallenge.dto_s.UserDTO;
import com.example.dabadoccodingchallenge.entitys.User;
import com.example.dabadoccodingchallenge.exceptions.AppException;
import com.example.dabadoccodingchallenge.repositorys.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;


@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.key:secret-key}")
    private String secretKey;

    private final UserRepository userRepository;

    @PostConstruct
    protected  void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO userDTO){

        Date now=new Date();
        Date validity=new Date(now.getTime() + 3_600_000);
        return JWT.create()
                .withIssuer(userDTO.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("username", userDTO.getUsername())
                //.withClaim()
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token){

        Algorithm algorithm=Algorithm.HMAC256(secretKey);
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        DecodedJWT decodedJWT= jwtVerifier.verify(token);

        UserDTO userDTO= new UserDTO();
        userDTO.setUsername(decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(userDTO,null, Collections.emptyList());
    }
    public Authentication validateTokenStrenght(String token) throws AppException {

        Algorithm algorithm=Algorithm.HMAC256(secretKey);
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        DecodedJWT decodedJWT= jwtVerifier.verify(token);


        User user=userRepository.findUserByUsername(decodedJWT.getIssuer())
                .orElseThrow(()->new AppException("user not found with jwt token",
                        HttpStatus.NOT_FOUND));

       /* UserDTO userDTO= new UserDTO();
        userDTO.setUsername(decodedJWT.getIssuer());
*/
        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

}
