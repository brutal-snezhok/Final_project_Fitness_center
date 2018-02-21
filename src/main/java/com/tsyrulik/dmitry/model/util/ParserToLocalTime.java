package com.tsyrulik.dmitry.model.util;

import java.time.LocalTime;

public class ParserToLocalTime {
    public static LocalTime parse(String string){
        String[] strings = string.split(":");
        LocalTime localTime = LocalTime.of(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        return localTime;

    }
}