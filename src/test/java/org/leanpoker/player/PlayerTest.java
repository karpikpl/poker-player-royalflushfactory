package org.leanpoker.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.leanpoker.player.dto.GameStateDto;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class PlayerTest {

	@Test
	@Ignore
	public void testBetRequest() throws Exception {

		GameStateDto gameState = new Gson().fromJson(
				"{\"key1\": \"value1\", \"key2\": \"value2\"}", GameStateDto.class);

		assertEquals(0, Player.betRequest(gameState));

	}

	@Test
	public void should_parse_gameState() throws IOException {

		// Arrange
		Player player = new Player();
		JsonParser parser = new JsonParser();

		URL resource = getClass().getResource("/gamestate.json");
		String jsonString = FileUtils.readFileToString(new File(resource.getFile()));

		GameStateDto gameState = new Gson().fromJson(jsonString, GameStateDto.class);

		// Act
		int result = player.betRequest(gameState);

		// Assert
		assertTrue("Bet cannot be negative", result >= 0);
	}
	
	@Test
	public void should_parse_gameState2() throws IOException {

		// Arrange
		Player player = new Player();
		JsonParser parser = new JsonParser();

		URL resource = getClass().getResource("/gamestate2.json");
		String jsonString = FileUtils.readFileToString(new File(resource.getFile()));

		GameStateDto gameState = new Gson().fromJson(jsonString, GameStateDto.class);

		// Act
		int result = player.betRequest(gameState);

		// Assert
		assertTrue("Bet cannot be negative", result >= 0);
	}
}
