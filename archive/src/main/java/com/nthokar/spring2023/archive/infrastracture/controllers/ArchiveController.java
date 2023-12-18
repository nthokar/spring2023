package com.nthokar.spring2023.archive.infrastracture.controllers;

import com.nthokar.spring2023.archive.app.entities.Game;
import com.nthokar.spring2023.archive.infrastracture.services.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/archive")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    @GetMapping("/game")
    public ResponseEntity<Game> getGame(@RequestParam String gameId) {
        var game = archiveService.get(gameId);
        return game.isPresent() ?
                ResponseEntity.ok().body(archiveService.get(gameId).get()) :
                ResponseEntity.badRequest().body(null);
    }
    @GetMapping("/save")
    public ResponseEntity<String> getGame(@RequestParam Game game) {
        try {
            archiveService.save(game);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
