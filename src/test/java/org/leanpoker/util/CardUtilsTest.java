package org.leanpoker.util;

import org.junit.Assert;
import org.junit.Test;
import org.leanpoker.player.dto.CardDto;

public class CardUtilsTest {
	@Test
	public void testConvertCardrankToChar() throws Exception {
		Assert.assertEquals('2', CardUtils.convertCardrankToChar(new CardDto("2", "spades")));
		Assert.assertEquals('7', CardUtils.convertCardrankToChar(new CardDto("7", "spades")));
		Assert.assertEquals('T', CardUtils.convertCardrankToChar(new CardDto("10", "spades")));
		Assert.assertEquals('K', CardUtils.convertCardrankToChar(new CardDto("K", "spades")));
	}

	@Test
	public void testAreTheSameColors() throws Exception {
		Assert.assertTrue(CardUtils.areTheSameColors(new CardDto("2", "spades"), new CardDto("2", "spades")));
		Assert.assertFalse(CardUtils.areTheSameColors(new CardDto("2", "spades"), new CardDto("2", "diamonts")));
	}
}
