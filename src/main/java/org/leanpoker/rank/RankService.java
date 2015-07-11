package org.leanpoker.rank;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RankService {

	public static Rank checkRank(final List<Card> cards) {
		try {
			final JsonArray jsonCards = createJsonCards(cards);
			final HttpResponse response = sendRequest(jsonCards);

			final InputStream input = response.getEntity().getContent();

			final JsonElement json = new JsonParser().parse(new InputStreamReader(input));

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
		final JsonArray jsonKickers = jsonObject.get("kickers").getAsJsonArray();
		for (int i = 0; i < jsonKickers.size(); i++) {
			kickers.add(jsonKickers.get(i).getAsInt());
		}
		rank.setKickers(kickers);

		final List<Card> cardsUsed = new ArrayList<Card>();
		final JsonArray jsonCardsUsed = jsonObject.get("cards_used").getAsJsonArray();
		for (int i = 0; i < jsonCardsUsed.size(); i++) {
			final char cardRank = jsonCardsUsed.get(i).getAsJsonObject().get("rank").getAsCharacter();
			final String cardSuit = jsonCardsUsed.get(i).getAsJsonObject().get("suit").getAsString();
			final Card card = new Card(cardRank, cardSuit);
			cardsUsed.add(card);
		}
		rank.setCardsUsed(cardsUsed);

		final List<Card> allCards = new ArrayList<Card>();
		final JsonArray jsonAllCards = jsonObject.get("cards").getAsJsonArray();
		for (int i = 0; i < jsonAllCards.size(); i++) {
			final char cardRank = jsonAllCards.get(i).getAsJsonObject().get("rank").getAsCharacter();
			final String cardSuit = jsonAllCards.get(i).getAsJsonObject().get("suit").getAsString();
			final Card card = new Card(cardRank, cardSuit);
			allCards.add(card);
		}
		rank.setCards(allCards);
		return rank;
	}

	private static HttpResponse sendRequest(final JsonArray jsonCards) throws URISyntaxException, IOException,
			ClientProtocolException {
		final URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("rainman.leanpoker.org").setPath("/rank")
				.setParameter("cards", jsonCards.toString());

		final URI uri = builder.build();
		final HttpGet httpget = new HttpGet(uri);
		final HttpClient httpClient = HttpClientBuilder.create().build();
		final HttpResponse response = httpClient.execute(httpget);
		return response;
	}

	private static JsonArray createJsonCards(final List<Card> cards) {
		final JsonArray jsonCards = new JsonArray();
		for (final Card card : cards) {
			final JsonObject cardJson = new JsonObject();
			cardJson.addProperty("rank", card.getCardRank());
			cardJson.addProperty("suit", card.getCardSuit());
			jsonCards.add(cardJson);
		}
		return jsonCards;
	}
}
