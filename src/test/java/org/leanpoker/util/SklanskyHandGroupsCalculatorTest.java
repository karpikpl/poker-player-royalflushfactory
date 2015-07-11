package org.leanpoker.util;

import static org.fest.assertions.api.Assertions.*;
import org.junit.Test;

public class SklanskyHandGroupsCalculatorTest {

	private SklanskyHandGroupsCalculator calculator = new SklanskyHandGroupsCalculator();
	
	
	@Test
	public void shouldCalculateSampleValue() {
		
		// given
		
		// when
		int rank1 = calculator.getRank('A', 'A', true);
		int rank8 = calculator.getRank('5', '4', false);
		int rank8a = calculator.getRank('4', '5', false);
		
		// then
		assertThat(rank1).isEqualTo(8);
		assertThat(rank8).isEqualTo(1);
		assertThat(rank8a).isEqualTo(1);
	}
	
	@Test
	public void shouldEveryPairReturnResult() {
		
		// given
		char[] cards = {'1','2','3','4','5','6','7','8','9','T','J','Q','K','A'}; 
		
		// when
		for (char c1 : cards) {
			for (char c2 : cards) {
				Integer rankS = calculator.getRank(c1, c2, true);
				Integer rankO = calculator.getRank(c1, c2, false);
				assertThat(rankS).isNotNull();
				assertThat(rankO).isNotNull();				
			}
		}
		
		// then
		
		
	}
}
