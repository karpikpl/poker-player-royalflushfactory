package org.leanpoker.player;

import java.util.Random;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Mad player";

    public static int betRequest(JsonElement request) {
        Random random = new Random();
		return random.nextInt(100) * random.nextInt(10);
    }

    public static void showdown(JsonElement game) {
    }
}
