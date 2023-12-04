package com.nthokar.spring2023.archive.infrastracture.services;

import com.nthokar.spring2023.archive.app.entities.Position;
import com.nthokar.spring2023.archive.app.repos.GameRepo;
import com.nthokar.spring2023.archive.app.services.IStatisticsService;
import com.nthokar.spring2023.archive.domain.IGame;
import com.nthokar.spring2023.archive.domain.IMove;
import com.nthokar.spring2023.archive.domain.IOpening;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class StatisticsService implements IStatisticsService {

    @Autowired
    GameRepo gameRepo;


    @Override
    public IGame getGamesWithOpenings(Position opening) {
        return null;
    }

    @Override
    public HashMap<String, Double> getCommonMoves(String position) {
        var games = gameRepo.findAllByPositions(position);
        HashMap<String, Double> moves = new HashMap<>();
        games.stream().forEach(
                game -> game.getPositions().stream()
                        .filter(p -> p.getPosition().equals(position))
                        .forEach(p -> {
                            var move =  game.getMoves()
                                    .stream()
                                    .filter(m -> Objects.equals(m.getIndex(), p.getIndex()))
                                    .findFirst()
                                    .get();
                                moves.put(move.getMove(),
                                        moves.containsKey(move.getMove()) ? moves.get(move.getMove()) + 1 : 1);
                            })
        );
        var count = games.stream().filter(
                game -> game.getPositions().stream()
                        .anyMatch(p -> p.getPosition().equals(position))
        ).count();
        for (var move:moves.keySet()) {
            moves.put(move, moves.get(move)/count);
        }
        return moves;
    }

    @Override
    public List<IMove> getCommonMoves(Position position, Predicate<IGame> gamePredicate) {
        return null;
    }
}
