package com.dinesh.OauthAuthorizationserver.service;

import com.dinesh.OauthAuthorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDerailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user  = userRepository.findByEmail(username) ;
        if(user == null )
        {
            throw new UsernameNotFoundException(username + "not found") ;
        }
        return  user ;
    }
}
