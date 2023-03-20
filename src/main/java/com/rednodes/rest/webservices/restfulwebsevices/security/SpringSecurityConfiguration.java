package com.rednodes.rest.webservices.restfulwebsevices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1 all requests should be authenticated
        http.authorizeRequests(auth ->
                auth.anyRequest().authenticated()
        );
        // 2 if a request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());
        //3 disable csrf
        http.csrf().disable();
        return http.build();
    }
}
