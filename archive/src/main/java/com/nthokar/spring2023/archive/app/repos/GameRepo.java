package com.nthokar.spring2023.archive.app.repos;

import com.nthokar.spring2023.archive.app.entities.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GameRepo extends CrudRepository<Game, String> {
    @Query("SELECT g FROM Game g WHERE :position in (SELECT p FROM g.positions p)")
    List<Game> findAllByPositions(@Param("position") String position);
}
