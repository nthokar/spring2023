package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class MatchmakingService {
    @Autowired GameService gameService;

    private static class MatchMakingHashMap{
        HashMap<String, HashMap<String, Game.Builder>> games = new HashMap<>();

        public Game.Builder getBuilder(String gameType, String gameId) {
            return games.get(gameType).get(gameId);
        }
        public Game.Builder deleteBuilder(String gameType, String gameId){
            return games.get(gameType).remove(gameId);
        }

        public void putBuilder(Game.Builder builder){
            var builderType = builder.getClass().getName();
            if (games.containsKey(builderType)){
                games.get(builderType).put(builder.id, builder);
            }
            else {
                games.put(builderType, new HashMap<>());
                games.get(builderType).put(builder.id, builder);
            }
        }
    }

    MatchMakingHashMap matchMakingHashMap = new MatchMakingHashMap();


    public void connectToGame(Game.Builder game, UserEntity userEntity) {
        if (Objects.nonNull(game.blackPlayer) && Objects.nonNull(game.whitePlayer)){
            //log
            throw new RuntimeException();
        }
        var player = new WebPlayer(userEntity);
        if (Objects.isNull(game.whitePlayer))
            game.setWhitePlayer(player);
        else
            game.setBlackPLayer(player);
        buildGame(game);
    }
    public void findOpponent(Game.Builder game, UserEntity userEntity) {
        //TODO
        buildGame(game);
    }
    public void buildGame(Game.Builder game) {
        if (Objects.nonNull(game.blackPlayer) && Objects.nonNull(game.whitePlayer)){
            matchMakingHashMap.getBuilder(game.getClass().getSimpleName(), game.id);
            gameService.createNewGame(game);
        }
    }
}
