package com.nthokar.spring2023.archive.infrastracture;

import com.nthokar.spring2023.archive.app.entities.Game;
import com.nthokar.spring2023.archive.app.services.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @Autowired
    ArchiveService archiveService;

    @GetMapping("/game")
    public ResponseEntity<Game> getGame(@RequestParam Long gameId) {
        var game = archiveService.GetGame(gameId);
        return game.isPresent() ?
                ResponseEntity.ok().body(archiveService.GetGame(gameId).get()) :
                ResponseEntity.badRequest().body(null);
    }
}
