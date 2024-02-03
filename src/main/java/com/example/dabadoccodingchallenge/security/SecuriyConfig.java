package com.example.dabadoccodingchallenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecuriyConfig {

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,"/auth").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/dabadoc/users/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/dabadoc/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/dabadoc/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/dabadoc/users/**").hasRole("ADMIN")
                        .anyRequest().permitAll()

                );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf->csrf.disable());

        return http.build();
    }

   /* @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return  new JdbcUserDetailsManager(dataSource);
    }*/


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
