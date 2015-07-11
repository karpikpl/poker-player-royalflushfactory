package org.leanpoker.rank;

import java.util.ArrayList;
import java.util.List;

public class Rank {
	private int rank;
	private int value;
	private int secondValue;
	private List<Integer> kickers = new ArrayList<Integer>();
	private List<Card> cardsUsed = new ArrayList<Card>();
	private List<Card> cards = new ArrayList<Card>();

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

	@Override
	public String toString() {
		return "Rank[rank=" + this.rank + ",value=" + this.value + ",secondValue=" + this.secondValue + ",kickers="
				+ this.kickers + ",cardsUsed=" + this.cardsUsed + ",cards=" + this.cards + "]";
	}
}
