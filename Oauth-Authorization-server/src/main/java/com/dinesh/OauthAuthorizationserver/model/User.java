package com.dinesh.OauthAuthorizationserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String email ;
    private String firstName;
    private String lastName ;

    private String passWord ;

    private String role  ;

    private boolean isEnabled ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }
}
