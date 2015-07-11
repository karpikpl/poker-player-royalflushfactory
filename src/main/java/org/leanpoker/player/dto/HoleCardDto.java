package org.leanpoker.player.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class HoleCardDto {

	@Expose
	private String rank;
	@Expose
	private String suit;

	/**
	 *
	 * @return The rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 *
	 * @param rank
	 *            The rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 *
	 * @return The suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 *
	 * @param suit
	 *            The suit
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

}