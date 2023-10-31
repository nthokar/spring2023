package com.nthokar.spring2023.main.domain.chess.game;

import java.util.HashMap;

public class TimeStandards {
    public record TimeStandard(String name, float playerTime, float extraTime) {
    }

    //standard time formats for chess game
    public static final HashMap<String, TimeStandard> TIME_STANDARD = new HashMap<>();
    static {
        TIME_STANDARD.put("5+2", new TimeStandard("5+2", 300, 2));
        TIME_STANDARD.put("10+5", new TimeStandard("10+5", 600, 5));
    }

}
