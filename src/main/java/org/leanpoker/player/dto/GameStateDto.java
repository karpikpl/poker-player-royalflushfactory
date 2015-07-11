package org.leanpoker.player.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GameStateDto {

	@SerializedName("tournament_id")
	@Expose
	private String tournamentId;
	@SerializedName("game_id")
	@Expose
	private String gameId;
	@Expose
	private Integer round;
	@SerializedName("bet_index")
	@Expose
	private Integer betIndex;
	@SerializedName("small_blind")
	@Expose
	private Integer smallBlind;
	@SerializedName("current_buy_in")
	@Expose
	private Integer currentBuyIn;
	@Expose
	private Integer pot;
	@SerializedName("minimum_raise")
	@Expose
	private Integer minimumRaise;
	@Expose
	private Integer dealer;
	@Expose
	private Integer orbits;
	@SerializedName("in_action")
	@Expose
	private Integer inAction;
	@Expose
	private List<PlayerDto> players = new ArrayList<PlayerDto>();
	@SerializedName("community_cards")
	@Expose
	private List<CommunityCardDto> communityCards = new ArrayList<CommunityCardDto>();

	/**
	 *
	 * @return The tournamentId
	 */
	public String getTournamentId() {
		return tournamentId;
	}

	/**
	 *
	 * @param tournamentId
	 *            The tournament_id
	 */
	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	/**
	 *
	 * @return The gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 *
	 * @param gameId
	 *            The game_id
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 *
	 * @return The round
	 */
	public Integer getRound() {
		return round;
	}

	/**
	 *
	 * @param round
	 *            The round
	 */
	public void setRound(Integer round) {
		this.round = round;
	}

	/**
	 *
	 * @return The betIndex
	 */
	public Integer getBetIndex() {
		return betIndex;
	}

	/**
	 *
	 * @param betIndex
	 *            The bet_index
	 */
	public void setBetIndex(Integer betIndex) {
		this.betIndex = betIndex;
	}

	/**
	 *
	 * @return The smallBlind
	 */
	public Integer getSmallBlind() {
		return smallBlind;
	}

	/**
	 *
	 * @param smallBlind
	 *            The small_blind
	 */
	public void setSmallBlind(Integer smallBlind) {
		this.smallBlind = smallBlind;
	}

	/**
	 *
	 * @return The currentBuyIn
	 */
	public Integer getCurrentBuyIn() {
		return currentBuyIn;
	}

	/**
	 *
	 * @param currentBuyIn
	 *            The current_buy_in
	 */
	public void setCurrentBuyIn(Integer currentBuyIn) {
		this.currentBuyIn = currentBuyIn;
	}

	/**
	 *
	 * @return The pot
	 */
	public Integer getPot() {
		return pot;
	}

	/**
	 *
	 * @param pot
	 *            The pot
	 */
	public void setPot(Integer pot) {
		this.pot = pot;
	}

	/**
	 *
	 * @return The minimumRaise
	 */
	public Integer getMinimumRaise() {
		return minimumRaise;
	}

	/**
	 *
	 * @param minimumRaise
	 *            The minimum_raise
	 */
	public void setMinimumRaise(Integer minimumRaise) {
		this.minimumRaise = minimumRaise;
	}

	/**
	 *
	 * @return The dealer
	 */
	public Integer getDealer() {
		return dealer;
	}

	/**
	 *
	 * @param dealer
	 *            The dealer
	 */
	public void setDealer(Integer dealer) {
		this.dealer = dealer;
	}

	/**
	 *
	 * @return The orbits
	 */
	public Integer getOrbits() {
		return orbits;
	}

	/**
	 *
	 * @param orbits
	 *            The orbits
	 */
	public void setOrbits(Integer orbits) {
		this.orbits = orbits;
	}

	/**
	 *
	 * @return The inAction
	 */
	public Integer getInAction() {
		return inAction;
	}

	/**
	 *
	 * @param inAction
	 *            The in_action
	 */
	public void setInAction(Integer inAction) {
		this.inAction = inAction;
	}

	/**
	 *
	 * @return The players
	 */
	public List<PlayerDto> getPlayers() {
		return players;
	}

	/**
	 *
	 * @param players
	 *            The players
	 */
	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}

	/**
	 *
	 * @return The communityCards
	 */
	public List<CommunityCardDto> getCommunityCards() {
		return communityCards;
	}

	/**
	 *
	 * @param communityCards
	 *            The community_cards
	 */
	public void setCommunityCards(List<CommunityCardDto> communityCards) {
		this.communityCards = communityCards;
	}

}