package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.leanpoker.rank.Card;
import org.leanpoker.rank.RankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

    static final String VERSION = "Aggressive  Java folding player";

    public static int betRequest(final JsonElement request) {
        final int currentBuyIn = getOrElse(request, "current_buy_in", 0);
        final int minimumRaise = getOrElse(request, "minimum_raise", 1);
        final int round = getOrElse(request, "round", 0);

        final int inAction = request.getAsJsonObject().get("in_action").getAsInt();

        final JsonObject currentPlayer = findCurrentPlayer(request, inAction);
        int stack = getOrElse(currentPlayer, "stack", 0);

        JsonArray hole_cards = currentPlayer.getAsJsonArray("hole_cards");
        List<Card> cards = new ArrayList<Card>();
        for(JsonElement card: hole_cards) {
            char rank = card.getAsJsonObject().get("rank").getAsCharacter();
            String suit = card.getAsJsonObject().get("suit").getAsString();
            cards.add(new Card(rank, suit));
        }

        RankService.checkRank(cards);

        int bet = getOrElse(currentPlayer, "bet", 0);

        int newBet = currentBuyIn - bet + minimumRaise;
        if(newBet > stack / 2 ) {
            return 0;
        } else {
            return newBet;
        }

    }

    private static int getOrElse(JsonElement request, String property, int defaultValue) {
        JsonElement element = request.getAsJsonObject().get(property);
        if(element == null) {
            return defaultValue;
        } else {
            return element.getAsInt();
        }
    }

    private static JsonObject findCurrentPlayer(JsonElement request, int inAction) {
        for(JsonElement element : request.getAsJsonObject().get("players").getAsJsonArray()){
            JsonObject player = element.getAsJsonObject();
            int playerId = player.get("id").getAsInt();
            if(playerId == inAction) {
                return player;
            }
        }
        throw new IllegalStateException("current player not found for given id: " + inAction);
    }

    public static void showdown(JsonElement game) {
    }
}
