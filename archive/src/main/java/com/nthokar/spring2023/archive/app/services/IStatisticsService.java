package com.nthokar.spring2023.archive.app.services;

import com.nthokar.spring2023.archive.domain.IGame;
import com.nthokar.spring2023.archive.domain.IMove;
import com.nthokar.spring2023.archive.app.entities.Position;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public interface IStatisticsService {
    IGame getGamesWithOpenings(Position opening);
    HashMap<String, Double> getCommonMoves(String position);
    List<IMove> getCommonMoves(Position position, Predicate<IGame> gamePredicate);
}
