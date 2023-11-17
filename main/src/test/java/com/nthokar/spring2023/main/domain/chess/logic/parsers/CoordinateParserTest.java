package com.nthokar.spring2023.main.domain.chess.logic.parsers;

import com.nthokar.spring2023.main.domain.chess.logic.Coordinate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateParserTest {

    @Test
    void parse() {
        var coord = new Coordinate(1,1);
        Assert.assertEquals(CoordinateParser.parseToString(coord), "a1");
        coord = new Coordinate(5,2);
        Assert.assertEquals(CoordinateParser.parseToString(coord), "e2");
    }
}