package com.nthokar.spring2023.userauth.infrastructure.config;

import com.nthokar.spring2023.userauth.application.entities.Role;
import com.nthokar.spring2023.userauth.application.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUsrDetails implements UserDetails {

    private final User user;

    public CustomUsrDetails(User user) {
        this.user = user;
    }

    public String getId() {
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {authorities.add(new SimpleGrantedAuthority(role.getRoleName()));}
        return authorities;
    }

    @Override
    public String getPassword() {return user.getPassword();}

    @Override
    public String getUsername() {return user.getName();}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}