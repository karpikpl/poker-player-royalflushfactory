package org.leanpoker.rank;

public class Card {

	private final char cardRank;

	private final String cardSuit;

	public Card(final char cardRank, final String cardSuit) {
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}

	public char getCardRank() {
		return this.cardRank;
	}

	public String getCardSuit() {
		return this.cardSuit;
	}

	@Override
	public String toString() {
		return "Card[cardRank=" + this.cardRank + ",cardSuit=" + this.cardSuit + "]";
	}
}
