package org.leanpoker.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PlayerTest {

	@Test
	@Ignore
	public void testBetRequest() throws Exception {

		JsonElement jsonElement = new JsonParser()
				.parse("{\"key1\": \"value1\", \"key2\": \"value2\"}");

		assertEquals(0, Player.betRequest(jsonElement));

	}

	@Test
	public void should_parse_gameState() throws IOException {

		// Arrange
		Player player = new Player();
		JsonParser parser = new JsonParser();

		URL resource = getClass().getResource("/gamestate.json");
		String jsonString = FileUtils.readFileToString(new File(resource.getFile()));

		JsonElement gameState = parser.parse(jsonString);

		// Act
		int result = player.betRequest(gameState);

		// Assert
		assertTrue("Bet cannot be negative", result > 0);
	}
}
