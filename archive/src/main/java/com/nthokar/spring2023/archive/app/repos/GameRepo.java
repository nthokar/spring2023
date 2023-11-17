package com.nthokar.spring2023.archive.app.repos;

import com.nthokar.spring2023.archive.app.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository<Game, Long> {

}
