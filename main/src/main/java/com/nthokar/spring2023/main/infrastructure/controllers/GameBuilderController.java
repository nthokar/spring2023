package com.nthokar.spring2023.main.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.application.services.GameBuilderService;
import com.nthokar.spring2023.main.application.services.UserService;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/game/build")
public class GameBuilderController {

    @Autowired GameBuilderService gameBuilderService;
    @Autowired UserService userService;
    @PostMapping("/invite")
    public void invitePlayer(@RequestParam String gameId,
                             @RequestParam String invitedPlayerId){
        var game = gameBuilderService.getGame(gameId);
        var user = userService.getUser(invitedPlayerId);
        if (user.isEmpty()){
            throw new RuntimeException("user not found");
        }
        //TODO invite friend via service
        //gameBuilderService.connectToGame(game, user.get());
    }


    @PostMapping("/buildGame/{userId}")
    public String buildGame(@PathVariable String userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<UserEntity> user = userService.getUser(userId);
        if (user.isEmpty()) throw new RuntimeException();
        WebPlayer whitePlayer = new WebPlayer(user.get());
        Game.Builder builder = gameBuilderService
                .createClassicBoard()
                .setWhitePlayer(whitePlayer);
        gameBuilderService.createBuilder(builder);
        return objectMapper.writeValueAsString(builder);
    }

    @PostMapping("/buildGame/{game_id}")
    public ResponseEntity<String> setTimer(
            @PathVariable String game_id,
            @RequestParam Integer time,
            @RequestParam Integer extra_time) {
        ObjectMapper objectMapper = new ObjectMapper();
        var game = gameBuilderService.getGame(game_id);
        gameBuilderService.setTimer(game, time, extra_time);
        return ResponseEntity.ok("");
    }

    @GetMapping("/buildGame/{id}")
    public Game.Builder getGame(@PathVariable String id){
        return gameBuilderService.getGame(id);
    }
}
