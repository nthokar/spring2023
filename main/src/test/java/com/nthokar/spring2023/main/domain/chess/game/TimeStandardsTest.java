package com.nthokar.spring2023.main.domain.chess.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeStandardsTest {

    @Test
    void parse() {
        var timeStandard = TimeStandard.parse("10+5");
        // 3 and 13 prime numbers, thats need to exclude multiply answers
        Assertions.assertEquals(10 * 60 * 3 + 5 * 13,
                timeStandard.playerTime * 3 + timeStandard.extraTime * 13,
                1e-4);
    }
}