package org.leanpoker.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://en.wikipedia.org/wiki/Texas_hold_%
 * 27em_starting_hands#Sklansky_hand_groups
 */
public class SklanskyHandGroupsCalculator {

	public int getRank(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private final static Map<String, Integer> initData = new HashMap<String, Integer>();

	private static void initRow(Integer rank, String... pairs) {
		for (String pair : pairs) {
			addPair(rank, pair);
		}
	}

	private static void addPair(Integer rank, String pair) {
		if (pair.length() == 3) {
			initData.put(pair, rank);
			initData.put("" + pair.charAt(1) + pair.charAt(0) + pair.charAt(2),
					rank);
			return;
		}
		if (pair.length() == 2) {
			addPair(rank, pair + "o");
			addPair(rank, pair + "s");
			return;
		}
		throw new IllegalArgumentException("Incorrect pair: " + pair);
	}

	static {
		initRow(1, "AA", "KK", "QQ", "JJ", "AKs");
		initRow(2, "TT", "AQs", "AJs", "KQs", "AKo");
		initRow(3, "99", "ATs", "KJs", "QJs", "JTs", "AQo");
		initRow(4, "88", "KTs", "QTs", "J9s", "T9s", "98s", "AJo", "KQo");
		initRow(5, "77", "A9s", "A8s", "A7s", "A6s", "A5s", "A4s", "A3s",
				"A2s", "Q9s", "T8s", "97s", "87s", "76s", "KJo", "QJo", "JTo");
		initRow(6, "66", "55", "K9s", "J8s", "86s", "75s", "54s", "ATo", "KTo",
				"QTo");
		initRow(7, "44", "33", "22", "K8s", "K7s", "K6s", "K5s", "K4s", "K3s",
				"K2s", "Q8s", "T7s", "64s", "53s", "43s", "J9o", "T9o", "89o");
		initRow(8, "J7s", "96s", "85s", "74s", "72s", "42s", "32s", "A9o",
				"K9o", "Q9o", "J8o", "T8o", "87o", "76o", "65o", "54o");
	}

	public Integer getRank(char card1, char card2, boolean same) {
		String key = "" + card1 + card2 + (same ? 's' : 'o');
		Integer rank = initData.get(key);
		return rank != null ? (9 - rank) : 0;
	}

}
