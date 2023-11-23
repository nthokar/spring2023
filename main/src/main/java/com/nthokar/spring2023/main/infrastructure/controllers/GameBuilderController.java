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

    @PostMapping("/buildGame/createGame/{userId}")
    public String createClassicBuilder(@RequestParam String userId,
                                       @RequestParam String timeStandard) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<UserEntity> user = userService.getUser(userId);
        if (user.isEmpty()) throw new RuntimeException();
        WebPlayer whitePlayer = new WebPlayer(user.get());
        Game.Builder builder = gameBuilderService.createClassicBoard(whitePlayer);
        return objectMapper.writeValueAsString(builder);
    }


    @PostMapping("/buildGame/createGame/{userId}")
    public String createBuilder(@PathVariable String userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<UserEntity> user = userService.getUser(userId);
        if (user.isEmpty()) throw new RuntimeException();
        WebPlayer whitePlayer = new WebPlayer(user.get());
        Game.Builder builder = new Game.Builder();
        builder.setWhitePlayer(whitePlayer);
        return objectMapper.writeValueAsString(builder);
    }

    @PostMapping("/buildGame/build/{builderId}")
    public ResponseEntity<String> buildGame(@PathVariable String builderId) {
        try {
            gameBuilderService.build(builderId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/buildGame/{id}")
    public Game.Builder getGame(@PathVariable String id){
        return gameBuilderService.getGame(id);
    }
}
