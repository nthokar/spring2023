package com.nthokar.spring2023.main.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.main.application.repositories.GameRepository;
import com.nthokar.spring2023.main.application.services.GameService;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController("/game")
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    GameRepository gameRepository;

//    @GetMapping
//    public String currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//        return oAuth2AuthenticationToken.toString();
//    }
    @GetMapping("/game/{game_id}")
    public String getGame(@PathVariable String game_id) {
        ObjectMapper objectMapper = new ObjectMapper();
        var game =  gameService.getGame(game_id);
        try {
            return objectMapper.writeValueAsString(game);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/gamefromhistory/{game_id}")
    public String getGameFromHistory(@PathVariable String game_id) {
        ObjectMapper objectMapper = new ObjectMapper();
        var game = gameRepository.findByIdAndFetch(game_id);
        try {
            return objectMapper.writeValueAsString(game.isPresent() ? null : game.get());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
