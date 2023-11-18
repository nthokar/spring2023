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
        public HashMap<String, Game.Builder> getBuildersByType(String gameType) {
            return games.get(gameType);
        }
        public HashMap<String, HashMap<String, Game.Builder>> getBuilders() {
            return games;
        }
        public Game.Builder deleteBuilder(String gameType, String gameId){
            return games.get(gameType).remove(gameId);
        }

        public void deleteBuilder(Game.Builder builder){
            games.get(getBuilderType(builder)).remove(builder.id);
        }

        public static String getBuilderType(Game.Builder builder) {
            return builder.getClass().getName();
        }

        public void putBuilder(Game.Builder builder){
            var builderType = getBuilderType(builder);
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
    //@Async
    public HashMap<String, HashMap<String, String>> mergePlayers()  {
        log.info("merging...");
        HashMap<String, HashMap<String, String>> gamesToMerge = new HashMap<>();
        BiPredicate<Game.Builder, Game.Builder> predicate = (game1, game2) -> true;

        var builderTypes = matchMakingHashMap.getBuilders();
        for (var builderType:builderTypes.keySet()) {
            gamesToMerge.put(builderType, mergePlayersInType(builderType));
        }
        mergeGames(gamesToMerge);
        return gamesToMerge;
    }
    public HashMap<String, String> mergePlayersInType(String builderType)  {
        log.info(String.format("merging in Type: %s ...", builderType));
        BiPredicate<Game.Builder, Game.Builder> predicate = (game1, game2) -> true;
        var gamesToMergeInType = findGamesToMergeInType(builderType, predicate);
        mergeGames(builderType, gamesToMergeInType);
        return gamesToMergeInType;
    }

    private HashMap<String, String> findGamesToMergeInType(String builderType, BiPredicate<Game.Builder, Game.Builder> predicate) {
        var gamesToMerge = new HashMap<String, String>();
        var buildersInType = matchMakingHashMap.getBuildersByType(builderType);
        var buildersInTypeSet = buildersInType.keySet();
        for (var gameId1:buildersInTypeSet){
            var game1 =  buildersInType.get(gameId1);
            for (var gameId2:buildersInTypeSet){
                var game2 =  buildersInType.get(gameId2);
                if (predicate.test(game1, game2)
                        && !Objects.equals(gameId1, gameId2)
                        && !gamesToMerge.containsKey(gameId1)
                        && !gamesToMerge.containsKey(gameId2)
                        && buildersInType.containsKey(gameId1)
                        && buildersInType.containsKey(gameId2)) {
                    //if (mergedGames.containsKey(builderType)) mergedGames.get(builderType).put(gameId1, gameId2);
                    //else gamesToMerge.put(builderType, new HashMap<>(){{put(gameId1, gameId2);}});
                    gamesToMerge.put(gameId1, gameId2);
                }
            }
        }
        return gamesToMerge;
    }

    private void mergeGames(HashMap<String, HashMap<String, String>> mergedGames) {
        for (var gameType: mergedGames.keySet()){
            var gamesInType = mergedGames.get(gameType);
            for (var gameId:gamesInType.keySet()){
                var game1 = matchMakingHashMap.getBuilder(gameType, gameId);
                var game2 = matchMakingHashMap.getBuilder(gameType, gamesInType.get(gameId));
                mergeGame(game1, game2);
            }
        }
    }

    private void mergeGames(String gameType, HashMap<String, String> mergedGames) {
        for (var gameId:mergedGames.keySet()){
            var game1 = matchMakingHashMap.getBuilder(gameType, gameId);
            var game2 = matchMakingHashMap.getBuilder(gameType, mergedGames.get(gameId));
            mergeGame(game1, game2);
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
}
