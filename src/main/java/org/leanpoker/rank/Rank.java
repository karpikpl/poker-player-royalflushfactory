package org.leanpoker.rank;

import java.util.List;

public class Rank {
	private int rank;
	private int value;
	private int secondValue;
	private List<Integer> kickers;
	private List<Card> cardsUsed;
	private List<Card> cards;

	public int getRank() {
		return this.rank;
	}

	public void setRank(final int rank) {
		this.rank = rank;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	public int getSecondValue() {
		return this.secondValue;
	}

	public void setSecondValue(final int secondValue) {
		this.secondValue = secondValue;
	}

	public List<Integer> getKickers() {
		return this.kickers;
	}

	public void setKickers(final List<Integer> kickers) {
		this.kickers = kickers;
	}

	public List<Card> getCardsUsed() {
		return this.cardsUsed;
	}

	public void setCardsUsed(final List<Card> cardsUsed) {
		this.cardsUsed = cardsUsed;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(final List<Card> cards) {
		this.cards = cards;
	}

}
