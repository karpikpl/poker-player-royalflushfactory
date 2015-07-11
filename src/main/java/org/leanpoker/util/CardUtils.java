package org.leanpoker.util;

import org.leanpoker.rank.Card;

public class CardUtils {
	public static char convertCardrankToChar(final Card card) {
		if (card.getCardRank().equals("10")) {
			return 'T';
		}
		return card.getCardRank().charAt(0);
	}

	public static boolean areTheSameColors(final Card card1, final Card card2) {
		return card1.getCardSuit().equals(card2.getCardSuit());
	}
}
