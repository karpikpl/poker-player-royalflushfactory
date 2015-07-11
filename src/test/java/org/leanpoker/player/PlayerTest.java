package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    @Test
    @org.junit.Ignore
    public void testBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\"key1\": \"value1\", \"key2\": \"value2\"}");

        assertEquals(0, Player.betRequest(jsonElement));

    }

    @Test
    public void should_parse_gameState(){
        // Arrange
        Player player = new Player();
        JsonParser parser = new JsonParser();
        JsonElement gameState =  parser.parse("{\n" + "  \"players\":[\n" + "    {\n" + "      \"name\":\"Player 1\",\n"
                + "      \"stack\":1000,\n" + "      \"status\":\"active\",\n" + "      \"bet\":0,\n"
                + "      \"hole_cards\":[],\n" + "      \"version\":\"Version name 1\",\n" + "      \"id\":0\n"
                + "    },\n" + "    {\n" + "      \"name\":\"Player 2\",\n" + "      \"stack\":1000,\n"
                + "      \"status\":\"active\",\n" + "      \"bet\":0,\n" + "      \"hole_cards\":[],\n"
                + "      \"version\":\"Version name 2\",\n" + "      \"id\":1\n" + "    }\n" + "  ],\n"
                + "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n"
                + "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" + "  \"round\":0,\n" + "  \"bet_index\":0,\n"
                + "  \"small_blind\":10,\n" + "  \"orbits\":0,\n" + "  \"dealer\":0,\n" + "  \"community_cards\":[],\n"
                + "  \"current_buy_in\":0,\n" + "  \"in_action\":0\n" + "}");

        // Act
        int result = player.betRequest(gameState);

        // Assert
        assertTrue("Bet cannot be negative", result >0);
    }
}
