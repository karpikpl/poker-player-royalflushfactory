package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;

import org.leanpoker.player.dto.CommunityCardDto;
import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.HoleCardDto;
import org.leanpoker.player.dto.PlayerDto;
import org.leanpoker.rank.Card;
import org.leanpoker.rank.Rank;
import org.leanpoker.rank.RankService;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Player {

    	static final String VERSION = "Aggressive  Java folding player";

	public static int betRequest(GameStateDto gameState) {
		final int currentBuyIn = getOrElse(gameState.getCurrentBuyIn(), 0);
		final int minimumRaise = getOrElse(gameState.getMinimumRaise(), 1);
		final int round = getOrElse(gameState.getRound(), 0);

		final int inAction = gameState.getInAction();

		final PlayerDto currentPlayer = findCurrentPlayer(gameState, inAction);
		int stack = getOrElse(currentPlayer.getStack(), 0);

        List<Card> communityCards = convertCardsCommunity(gameState.getCommunityCards());

        List<HoleCardDto> hole_cards = currentPlayer.getHoleCards();
        List<Card> cards = new ArrayList<Card>() ;
        cards.addAll(convertCardsHoly(hole_cards));

        if(communityCards.size() >= 3) {
            cards.addAll(communityCards);
        }

		int rank = RankService.checkRank(cards).getRank();

		int bet = getOrElse(currentPlayer.getBet(), 0);

		int newBet = currentBuyIn - bet + minimumRaise;

		if (getPlayersInGame(gameState) > 2) {
            if(rank > 5) {
                return newBet;
            } else {
                return 0;
            }
		}

		return newBet;
	}

    private static List<Card> convertCardsHoly(List<HoleCardDto> hole_cards) {
        List<Card> cards = new ArrayList<Card>();
        for(HoleCardDto card: hole_cards) {
            String rank = card.getRank();
            String suit = card.getSuit();
            cards.add(new Card(rank, suit));
        }
        return cards;
    }

    private static List<Card> convertCardsCommunity(List<CommunityCardDto> communityCard) {
        List<Card> cards = new ArrayList<Card>();
        for(CommunityCardDto card: communityCard) {
            String rank = card.getRank();
            String suit = card.getSuit();
            cards.add(new Card(rank, suit));
        }
        return cards;
    }

    private static int getPlayersInGame(GameStateDto gameState) {
		int i = 0;
		for (org.leanpoker.player.dto.PlayerDto player : gameState.getPlayers()) {
			if (!player.getStatus().equals("out")) {
				i++;
			}
		}
		return i;
	}

    private static int getPlayersActive(GameStateDto gameState) {
        int i = 0;
        for (org.leanpoker.player.dto.PlayerDto player : gameState.getPlayers()) {
            if (!player.getStatus().equals("active")) {
                i++;
            }
        }
        return i;
    }

	private static int getOrElse(JsonElement request, String property, int defaultValue) {
		JsonElement element = request.getAsJsonObject().get(property);
		if (element == null) {
			return defaultValue;
		} else {
			return element.getAsInt();
		}
	}

	private static int getOrElse(Object element, int defaultValue) {
		if (element == null) {
			return defaultValue;
		} else {
			return (Integer) element;
		}
	}

	private static org.leanpoker.player.dto.PlayerDto findCurrentPlayer(GameStateDto gameState, int inAction) {
		for (org.leanpoker.player.dto.PlayerDto player : gameState.getPlayers()) {
			if ((player.getId() != null) && (player.getId() == inAction)) {
				return player;
			}
		}
		throw new IllegalStateException("current player not found for given id: " + inAction);
	}

	public static void showdown(JsonElement game) {
	}
}
