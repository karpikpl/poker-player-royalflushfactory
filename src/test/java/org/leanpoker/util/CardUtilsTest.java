package org.leanpoker.util;

import org.junit.Assert;
import org.junit.Test;
import org.leanpoker.rank.Card;

public class CardUtilsTest {
	@Test
	public void testConvertCardrankToChar() throws Exception {
		Assert.assertEquals('2', CardUtils.convertCardrankToChar(new Card("2", "spades")));
		Assert.assertEquals('7', CardUtils.convertCardrankToChar(new Card("7", "spades")));
		Assert.assertEquals('T', CardUtils.convertCardrankToChar(new Card("10", "spades")));
		Assert.assertEquals('K', CardUtils.convertCardrankToChar(new Card("K", "spades")));
	}

	@Test
	public void testAreTheSameColors() throws Exception {
		Assert.assertTrue(CardUtils.areTheSameColors(new Card("2", "spades"), new Card("2", "spades")));
		Assert.assertFalse(CardUtils.areTheSameColors(new Card("2", "spades"), new Card("2", "diamonts")));
	}
}
