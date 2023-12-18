package com.nthokar.spring2023.main.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.services.GameBuilderService;
import com.nthokar.spring2023.main.application.services.feign.UserService;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("/game/build")
public class GameBuilderController {
    //TODO REFACTOR!!!!
    /*
    instead of using users id, use their tokens via feign
     */

    @Autowired
    UserService userService;
    @Autowired GameBuilderService gameBuilderService;
    @PostMapping("/invite")
    public void invitePlayer(@RequestParam String gameId,
                             @RequestParam String invitedPlayerId){
        var game = gameBuilderService.getGame(gameId);
        var user = userService.getUser(invitedPlayerId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not found");
        }
        //TODO invite friend via service
        //gameBuilderService.connectToGame(game, user.get());
    }


    @PostMapping("/buildGame/createGame/{userId}")
    public String createBuilder(@PathVariable String userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var user = userService.getUser(userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not found");
        }
        WebPlayer whitePlayer = new WebPlayer(user);
        Game.Builder builder = gameBuilderService.createClassicBoard(whitePlayer);
        return objectMapper.writeValueAsString(builder);
    }

    @PostMapping("/buildGame/build/{builderId}")
    public String buildGame(@PathVariable String builderId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        gameBuilderService.build(builderId);
        return "ok";
    }

    @PostMapping("/buildGame/setTimer/{game_id}")
    public ResponseEntity<String> setTimer(
            @PathVariable String game_id,
            @RequestParam String timeEncoded) {
        var game = gameBuilderService.getGame(game_id);
        gameBuilderService.setTimer(game, timeEncoded);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buildGame/{id}")
    public Game.Builder getGame(@PathVariable String id){
        return gameBuilderService.getGame(id);
    }
}
