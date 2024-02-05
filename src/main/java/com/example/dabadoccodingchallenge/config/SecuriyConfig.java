package com.example.dabadoccodingchallenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecuriyConfig {

    private final UserAuthProvider userAuthProvider;
    @Autowired
    public SecuriyConfig(UserAuthProvider userAuthProvider) {
        this.userAuthProvider = userAuthProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(configurer ->
                configurer
                        //.requestMatchers(HttpMethod.GET,"/auth").hasAnyRole("USER","ADMIN")
                        //.requestMatchers(HttpMethod.GET,"/auth/get").hasAnyRole("USER","ADMIN")
                        /*.requestMatchers(HttpMethod.POST,"/dabadoc/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/dabadoc/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/dabadoc/users/**").hasRole("ADMIN")*/
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .anyRequest().permitAll()

                );
        http.httpBasic(Customizer.withDefaults());
        http.cors(cors -> cors.disable());
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(custmizer->{
           custmizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });


        return http.build();
    }



 /*   @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
//        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);

        return source;
    }*/



    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled  from users where username=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, role from roles where username=?"
        );


        return jdbcUserDetailsManager;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

}
