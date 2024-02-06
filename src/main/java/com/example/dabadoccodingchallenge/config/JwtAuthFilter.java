package com.example.dabadoccodingchallenge.config;

import com.example.dabadoccodingchallenge.exceptions.AppException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header!= null){
            String[] authElms =header.split(" ");
            if (authElms.length==2 && "Bearer".equals(authElms[0])){
                try{

                    if("GET".equals(request.getMethod())){
                        SecurityContextHolder.getContext()
                                .setAuthentication(userAuthProvider.validateToken(authElms[1]));

                    } else {
                        try {
                            SecurityContextHolder.getContext()
                                    .setAuthentication(userAuthProvider.validateTokenStrenght(authElms[1]));
                        } catch (AppException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    }catch (RuntimeException e){
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request,response);

    }
}
