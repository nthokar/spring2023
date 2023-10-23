package com.nthokar.spring2023.main.infrastructure.controllers;

import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game/build")
public class GameBuilderController {
    @PostMapping("/invite")
    public void invitePlayer(Game.Builder builder, String invitedPlayerId){

    }
}
