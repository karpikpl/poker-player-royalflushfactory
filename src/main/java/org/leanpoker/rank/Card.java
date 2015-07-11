package org.leanpoker.rank;

public class Card {

	private final String cardRank;

	private final String cardSuit;

	public Card(final String cardRank, final String cardSuit) {
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}

	public String getCardRank() {
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
