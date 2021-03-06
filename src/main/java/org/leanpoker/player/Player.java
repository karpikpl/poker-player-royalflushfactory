package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;

import org.leanpoker.player.dto.CardDto;
import org.leanpoker.player.dto.GameStateDto;
import org.leanpoker.player.dto.PlayerDto;
import org.leanpoker.rank.RankService;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Aggressive  Java folding player";

	public static int betRequest(GameStateDto gameState) {
        final int currentBuyIn = getOrElse(gameState.getCurrentBuyIn(), 0);
        final int minimumRaise = getOrElse(gameState.getMinimumRaise(), 1);
        final int round = getOrElse(gameState.getRound(), 0);

        final Integer inAction = gameState.getInAction();

        final PlayerDto currentPlayer = findCurrentPlayer(gameState, inAction);
        int stack = getOrElse(currentPlayer.getStack(), 0);

        List<CardDto> communityCards = gameState.getCommunityCards();

        List<CardDto> hole_cards = currentPlayer.getHoleCards();
        List<CardDto> cards = new ArrayList<CardDto>();
        cards.addAll(hole_cards);

        boolean flop = communityCards.size() >= 3;
        if (flop) {
            cards.addAll(communityCards);
        }

        int rank = RankService.checkRank(cards).getRank();

        int bet = getOrElse(currentPlayer.getBet(), 0);

        int call = currentBuyIn - bet;

        int playersActive = getPlayersActive(gameState);
        int playersInGame = getPlayersInGame(gameState);

        if(flop) {
            if(rank >= 3) {
                return call + (rank - 3) * minimumRaise;
            } else if(call > 0 && rank >= 2){
                return call;
            } else {
                return 0;
            }
        } else {
            if (rank - playersActive >= 3) {
                return call + (rank - 3) * minimumRaise;
            } else if (rank - playersActive >= 0) {
                return call;
            } else {
                return 0;
            }
        }


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
