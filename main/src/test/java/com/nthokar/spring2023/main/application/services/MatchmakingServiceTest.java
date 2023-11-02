package com.nthokar.spring2023.main.application.services;


import com.nthokar.spring2023.main.application.WebPlayer;
import com.nthokar.spring2023.main.application.entities.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
@ComponentScan(basePackages = "com.nthokar.spring2023.main.application.services")
@RunWith(SpringRunner.class)
class MatchmakingServiceTest {
    @Autowired
    MatchmakingService matchmakingService;
    @Autowired GameBuilderService gameBuilderService;

    @Test
    void mergePlayers() throws ExecutionException, InterruptedException {

        var player1 = new WebPlayer(new UserEntity());
        var player2 = new WebPlayer(new UserEntity());
        var game1 = gameBuilderService.createClassicBoard(player1).id;
        var game2 = gameBuilderService.createClassicBoard(player2).id;

        gameBuilderService.build(game1);
        gameBuilderService.build(game2);

        var future = matchmakingService.mergePlayers();

        Assert.assertTrue(future.size() > 0);

        System.out.println(

        );
    }
}