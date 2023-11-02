package com.nthokar.spring2023.main.infrastructure.controllers;

import com.nthokar.spring2023.main.infrastructure.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired RabbitMqProducer rabbit;
//    @GetMapping("/")
//    public Mono<String> index(@AuthenticationPrincipal Mono<OAuth2User> oauth2User) {
//        return oauth2User
//                .map(OAuth2User::getName)
//                .map(name -> String.format("Hi, %s", name));
//    }
}
