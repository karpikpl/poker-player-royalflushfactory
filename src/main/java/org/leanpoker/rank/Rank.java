package org.leanpoker.rank;

import java.util.ArrayList;
import java.util.List;

import org.leanpoker.player.dto.CardDto;

public class Rank {
	private int rank;
	private int value;
	private int secondValue;
	private List<Integer> kickers = new ArrayList<Integer>();
	private List<CardDto> cardsUsed = new ArrayList<CardDto>();
	private List<CardDto> cards = new ArrayList<CardDto>();

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

	public List<CardDto> getCardsUsed() {
		return this.cardsUsed;
	}

	public void setCardsUsed(final List<CardDto> cardsUsed) {
		this.cardsUsed = cardsUsed;
	}

	public List<CardDto> getCards() {
		return this.cards;
	}

	public void setCards(final List<CardDto> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Rank[rank=" + this.rank + ",value=" + this.value + ",secondValue=" + this.secondValue + ",kickers="
				+ this.kickers + ",cardsUsed=" + this.cardsUsed + ",cards=" + this.cards + "]";
	}
}
