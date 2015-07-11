package org.leanpoker.util;

import org.leanpoker.player.dto.CardDto;

public class CardUtils {
	public static char convertCardrankToChar(final CardDto card) {
		if (card.getRank().equals("10")) {
			return 'T';
		}
		return card.getRank().charAt(0);
	}

	public static boolean areTheSameColors(final CardDto card1, final CardDto card2) {
		return card1.getSuit().equals(card2.getSuit());
	}
}
