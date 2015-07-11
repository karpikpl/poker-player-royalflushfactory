package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;
import java.util.Random;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        return new Random().nextInt(100);
    }

    public static void showdown(JsonElement game) {
    }
}
