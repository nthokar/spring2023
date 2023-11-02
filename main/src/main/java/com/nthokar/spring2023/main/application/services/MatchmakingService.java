package com.nthokar.spring2023.main.application.services;

import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.entities.UserEntity;
import com.nthokar.spring2023.main.domain.chess.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.BiPredicate;

@Slf4j
@Service
@EnableAsync
@EnableScheduling
public class MatchmakingService {
    @Autowired GameService gameService;

    private static class MatchMakingHashMap{
        HashMap<String, HashMap<String, Game.Builder>> games = new HashMap<>();

        public Game.Builder getBuilder(String gameType, String gameId) {
            return games.get(gameType).get(gameId);
        }
        public HashMap<String, Game.Builder> getBuilderByType(String gameType) {
            return games.get(gameType);
        }
        public HashMap<String, HashMap<String, Game.Builder>> getBuilderTypes() {
            return games;
        }
        public Game.Builder deleteBuilder(String gameType, String gameId){
            return games.get(gameType).remove(gameId);
        }

        public Game.Builder deleteBuilder(Game.Builder builder){
            return games.get(builder.getClass().getName()).remove(builder.id);
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

    public void addToQueue(Game.Builder builder){
        matchMakingHashMap.putBuilder(builder);
        mergePlayers();
    }
    public void buildGame(Game.Builder game) {
        if (Objects.nonNull(game.blackPlayer) && Objects.nonNull(game.whitePlayer)){
            matchMakingHashMap.getBuilder(game.getClass().getName(), game.id);
            gameService.createNewGame(game);
            matchMakingHashMap.deleteBuilder(game);
        }
    }
    private void mergeGame(Game.Builder game1, Game.Builder game2) {
        if ((Objects.nonNull(game1.blackPlayer) && Objects.nonNull(game1.whitePlayer))
                || (Objects.nonNull(game2.blackPlayer) && Objects.nonNull(game2.whitePlayer))) {
            throw new RuntimeException("already two player in game");
        }
        matchMakingHashMap.deleteBuilder(game2);


        game1.blackPlayer  = game2.whitePlayer;

        buildGame(game1);

        log.info("game have been merged");
    }
    //@Async
    //@Scheduled(fixedDelay = 5000)
    public HashMap<String, HashMap<String, String>> mergePlayers()  {
        log.info("merging...");
        HashMap<String, HashMap<String, String>> mergedGames = new HashMap<>();
        BiPredicate<Game.Builder, Game.Builder> predicate = (game1, game2) -> true;

        var builders = matchMakingHashMap.getBuilderTypes();
        var keySet = builders.keySet();
        for (var key:keySet) {
            var buildersInType = matchMakingHashMap.getBuilderByType(key);
            var keySet2 = buildersInType.keySet();
            for (var gameId1:keySet2){
                var game1 =  buildersInType.get(gameId1);
                for (var gameId2:keySet2){
                    var game2 =  buildersInType.get(gameId2);
                    if (predicate.test(game1, game2)
                            && gameId1 != gameId2
                            && (!mergedGames.containsKey(key) || !mergedGames.get(key).containsKey(gameId1))
                            && (!mergedGames.containsKey(key) || !mergedGames.get(key).containsKey(gameId2))
                            && buildersInType.containsKey(gameId1)
                            && buildersInType.containsKey(gameId2)) {
                        if (mergedGames.containsKey(key)) mergedGames.get(key).put(gameId1, gameId2);
                        else mergedGames.put(key, new HashMap<>(){{put(gameId1, gameId2);}});
                    }
                }
            }
            for (var gameType:mergedGames.keySet()){
                var gamesInType = mergedGames.get(gameType);
                for (var gameId:gamesInType.keySet()){
                    var game1 = matchMakingHashMap.getBuilder(gameType, gameId);
                    var game2 = matchMakingHashMap.getBuilder(gameType, gamesInType.get(gameId));
                    mergeGame(game1, game2);
                }
            }
        }
        return mergedGames;
    }
}
