package org.leanpoker.rank;

public class Card {

	private final CardRank cardRank;

	private final CardSuit cardSuit;

	public Card(final CardRank cardRank, final CardSuit cardSuit) {
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}

	public CardRank getCardRank() {
		return this.cardRank;
	}

	public CardSuit getCardSuit() {
		return this.cardSuit;
	}

	public static enum CardRank {
		_2, _3, _4, _5, _6, _7, _8, _9, _10, J, Q, D, A;
	}

	public static enum CardSuit {
		spade, heart, diamond, club;
	}
}
