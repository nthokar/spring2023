package com.nthokar.spring2023.archive.app.services;

import com.nthokar.spring2023.archive.domain.Game;
import com.nthokar.spring2023.archive.domain.Move;
import com.nthokar.spring2023.archive.domain.Opening;
import com.nthokar.spring2023.archive.domain.Position;

import java.util.List;
import java.util.function.Predicate;

public interface StatisticsService {
    Game getGamesWithOpenings(Opening opening);
    List<Move> getCommonMoves(Position position);
    List<Move> getCommonMoves(Position position, Predicate<Game> gamePredicate);
}
