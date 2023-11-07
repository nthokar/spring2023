package com.nthokar.spring2023.userauth.infrastructure.config;

import com.nthokar.spring2023.userauth.application.entities.User;
import com.nthokar.spring2023.userauth.application.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomUsrDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByName(name).orElseThrow(()-> new UsernameNotFoundException("User with name = "+name+" not exist!"));
        return new CustomUsrDetails(user);
    }

    public void saveUser(String email, String password) {
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(password);
        var user = new User();
        user.setName(email);
        user.setPassword(encodedPass);
        userRepo.save(user);
    }
}