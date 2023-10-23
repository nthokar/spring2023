package com.nthokar.spring2023.main.infrastructure.controllers;

import com.nthokar.spring2023.main.domain.chess.game.Game;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController("/game")
public class GameController {

    //@Autowired GameService gameService;

//    @GetMapping
//    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
//    }

    @PostMapping("/new_game")
    public void buildNewGame(Game.Builder builder) {

    }
//    @GetMapping("/game/{game_id}")
//    public Game getGame(@PathVariable String game_id) {
//        return gameService.getGame(game_id);
//    }
}
