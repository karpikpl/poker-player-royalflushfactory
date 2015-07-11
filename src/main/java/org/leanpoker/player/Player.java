package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;

import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoleCardDto;
import org.leanpoker.player.dto.PlayerDto;
import org.leanpoker.rank.Card;
import org.leanpoker.rank.Rank;
import org.leanpoker.rank.RankService;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Brand new strategy";

    public static int betRequest(GameStateDto request) {
        final int currentBuyIn = getOrElse(request.getCurrentBuyIn(), 0);
        final int minimumRaise = getOrElse(request.getMinimumRaise(), 1);
        final int round = getOrElse(request.getRound(), 0);

        final int inAction = request.getInAction();

        final PlayerDto currentPlayer = findCurrentPlayer(request, inAction);
        int stack = getOrElse(currentPlayer.getStack(), 0);

        
        List<HoleCardDto> hole_cards = currentPlayer.getHoleCards();
        List<Card> cards = new ArrayList<Card>();
        for(HoleCardDto card: hole_cards) {
            char rank = card.getRank().charAt(0);
            String suit = card.getSuit();
            cards.add(new Card(rank, suit));
        }

        int rank = RankService.checkRank(cards).getRank();

        int bet = getOrElse(currentPlayer.getBet(), 0);

        int newBet = currentBuyIn - bet + minimumRaise;
        
        if (getPlayersInGame(request) > 2) {
        	return 0;
        }
        return newBet;
    }
    
    public static int betRequestAlt(GameStateDto request) {
    	final int currentBuyIn = getOrElse(request.getCurrentBuyIn(), 0);
        final int minimumRaise = getOrElse(request.getMinimumRaise(), 1);
        final int round = getOrElse(request.getRound(), 0);

        final int inAction = request.getInAction();

        final PlayerDto currentPlayer = findCurrentPlayer(request, inAction);
        int stack = getOrElse(currentPlayer.getStack(), 0);

        
        List<HoleCardDto> hole_cards = currentPlayer.getHoleCards();
        List<Card> cards = new ArrayList<Card>();
        for(HoleCardDto card: hole_cards) {
            char rank = card.getRank().charAt(0);
            String suit = card.getSuit();
            cards.add(new Card(rank, suit));
        }

        int rank = RankService.checkRank(cards).getRank();

        int bet = getOrElse(currentPlayer.getBet(), 0);

        int newBet = currentBuyIn - bet + minimumRaise;
        
        if (getPlayersInGame(request) > 2 && stack > 200) {
        	return 0;
        }
        return newBet;
    }

    private static int getPlayersInGame(GameStateDto gameState) {
    	int i=0;
    	for (org.leanpoker.player.dto.PlayerDto player : gameState.getPlayers()) {
            if(!player.getStatus().equals("out")) {
				i++;
            }
        }
    	return i;
	}

	private static int getOrElse(JsonElement request, String property, int defaultValue) {
        JsonElement element = request.getAsJsonObject().get(property);
        if(element == null) {
            return defaultValue;
        } else {
            return element.getAsInt();
        }
    }

    private static int getOrElse(Object element, int defaultValue) {
        if(element == null) {
            return defaultValue;
        } else {
            return (Integer)element;
        }
    }
    private static org.leanpoker.player.dto.PlayerDto findCurrentPlayer(GameStateDto gameState, int inAction) {
    	for (org.leanpoker.player.dto.PlayerDto player : gameState.getPlayers()) {
            if((player.getId()!=null)&&(player.getId() == inAction)) {
                return player;
            }
        }
        throw new IllegalStateException("current player not found for given id: " + inAction);
    }

    public static void showdown(JsonElement game) {
    }
}
