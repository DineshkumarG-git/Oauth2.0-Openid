package com.dinesh.OauthAuthorizationserver.config;

import com.dinesh.OauthAuthorizationserver.service.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class DefualtSecurityConfig {


    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Bean
    public SecurityFilterChain defaultSecurityConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(auth -> auth.anyRequest().authenticated() ).formLogin(Customizer.withDefaults());
        return  httpSecurity.build();
    }

    @Autowired
    public  void  bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManager)
    {
        authenticationManager.authenticationProvider(customAuthenticationProvider) ;
    }


}
