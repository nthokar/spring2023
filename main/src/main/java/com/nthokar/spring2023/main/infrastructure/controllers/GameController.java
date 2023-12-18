package com.nthokar.spring2023.main.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthokar.spring2023.main.application.DTO.archive.MoveDTO;
import com.nthokar.spring2023.main.application.repositories.GameRepository;
import com.nthokar.spring2023.main.application.services.GameService;
import com.nthokar.spring2023.main.infrastructure.RabbitMqProducer;
import com.nthokar.spring2023.main.infrastructure.config.CustomAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController("/game")
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    RabbitMqProducer mqProducer;

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

    @PostMapping("/game/processMove")
    public ResponseEntity<String> process(
            CustomAuthentication authentication,
            @RequestParam MoveDTO moveDTO) {
        try {
            gameService.processMove(moveDTO, authentication.getId());
            mqProducer.sendMessage(moveDTO);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
