package com.dinesh.OauthAuthorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDerailsService userDerailsService;



    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String credential = authentication.getCredentials().toString();
        UserDetails user = userDerailsService.loadUserByUsername(userName);
        return  checkPassWord(user , credential);


    }

    private Authentication checkPassWord(UserDetails userDetails , String rawPassWord)
    {
       if(passwordEncoder.matches(rawPassWord , userDetails.getPassword()))
       {
           return  new UsernamePasswordAuthenticationToken(userDetails.getUsername() , userDetails.getPassword() , userDetails.getAuthorities()) ;
       }
       else {
           throw new BadCredentialsException("Bad Credential") ;
       }

    }


    @Override
    public boolean supports(Class<?> authentication) {
         return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) ;
    }
}
