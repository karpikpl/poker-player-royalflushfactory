package org.leanpoker.rank;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.leanpoker.player.dto.CardDto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RankService {

	public static Rank checkRank(final List<CardDto> cards) {
		if (cards.size() == 2) {
			final CardDto card1 = cards.get(0);
			final CardDto card2 = cards.get(1);
			List<String> top = Arrays.asList("A", "K");
			List<String> strong = Arrays.asList("A", "K", "Q", "J");
			List<String> lessStrong = Arrays.asList("A", "K", "Q", "J", "10");
			List<String> weak = Arrays.asList("9", "8", "7");
			boolean isPair = card1.getRank().equals(card2.getRank());
			/*
			 * if(isPair && strong.contains(card1.getCardRank()) ){ Rank rank =
			 * new Rank(); rank.setRank(8); return rank; } else
			 * if(top.contains(card1.getCardRank()) &&
			 * top.contains(card2.getCardRank()) &&
			 * card1.getCardSuit().equals(card2.getCardSuit()) ) { Rank rank =
			 * new Rank(); rank.setRank(7); return rank; } else if (isPair &&
			 * card1.getCardRank().equals("10")) { Rank rank = new Rank();
			 * rank.setRank(6); return rank; } else if (isPair &&
			 * weak.contains(card1.getCardRank())) { Rank rank = new Rank();
			 * rank.setRank(3); return rank; } else
			 */if (isPair) {
				Rank rank = new Rank();
				rank.setRank(8);
				return rank;
			} else {
				Rank rank = new Rank();
				rank.setRank(0);
				return rank;
			}

		}
		try {
			final JsonArray jsonCards = createJsonCards(cards);
			final HttpResponse response = sendRequest(jsonCards);

			final InputStream input = response.getEntity().getContent();

			final JsonElement json = new JsonParser()
					.parse(new InputStreamReader(input));

			final Rank rank = parseJSON(json);

			return rank;
		} catch (final Exception e) {
			return new Rank();
		}
	}

	private static Rank parseJSON(final JsonElement json) {
		final Rank rank = new Rank();
		final JsonObject jsonObject = json.getAsJsonObject();
		rank.setRank(jsonObject.get("rank").getAsInt());
		rank.setValue(jsonObject.get("value").getAsInt());
		rank.setSecondValue(jsonObject.get("second_value").getAsInt());
		final List<Integer> kickers = new ArrayList<Integer>();
		final JsonArray jsonKickers = jsonObject.get("kickers")
				.getAsJsonArray();
		for (int i = 0; i < jsonKickers.size(); i++) {
			kickers.add(jsonKickers.get(i).getAsInt());
		}
		rank.setKickers(kickers);

		final List<CardDto> cardsUsed = new ArrayList<CardDto>();
		final JsonArray jsonCardsUsed = jsonObject.get("cards_used")
				.getAsJsonArray();
		for (int i = 0; i < jsonCardsUsed.size(); i++) {
			final String cardRank = jsonCardsUsed.get(i).getAsJsonObject()
					.get("rank").getAsString();
			final String cardSuit = jsonCardsUsed.get(i).getAsJsonObject()
					.get("suit").getAsString();
			final CardDto card = new CardDto(cardRank, cardSuit);
			cardsUsed.add(card);
		}
		rank.setCardsUsed(cardsUsed);

		final List<CardDto> allCards = new ArrayList<CardDto>();
		final JsonArray jsonAllCards = jsonObject.get("cards").getAsJsonArray();
		for (int i = 0; i < jsonAllCards.size(); i++) {
			final String cardRank = jsonAllCards.get(i).getAsJsonObject()
					.get("rank").getAsString();
			final String cardSuit = jsonAllCards.get(i).getAsJsonObject()
					.get("suit").getAsString();
			final CardDto card = new CardDto(cardRank, cardSuit);
			allCards.add(card);
		}
		rank.setCards(allCards);
		return rank;
	}

	private static HttpResponse sendRequest(final JsonArray jsonCards)
			throws URISyntaxException, IOException, ClientProtocolException {
		final URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("rainman.leanpoker.org")
				.setPath("/rank").setParameter("cards", jsonCards.toString());

		final URI uri = builder.build();
		final HttpGet httpget = new HttpGet(uri);
		final HttpClient httpClient = HttpClientBuilder.create().build();
		final HttpResponse response = httpClient.execute(httpget);
		return response;
	}

	private static JsonArray createJsonCards(final List<CardDto> cards) {
		final JsonArray jsonCards = new JsonArray();
		for (final CardDto card : cards) {
			final JsonObject cardJson = new JsonObject();
			cardJson.addProperty("rank", card.getRank());
			cardJson.addProperty("suit", card.getSuit());
			jsonCards.add(cardJson);
		}
		return jsonCards;

	}
}
