package game.playingcard;
import java.util.Comparator;

import game.core.components.Deck;
import game.playingcard.attribute.Rank;
import game.playingcard.attribute.Suit;
import game.playingcard.comparator.RankComparator;
import game.playingcard.comparator.SuitComparator;

public class PlayingCardDeck extends Deck<PlayingCard> {
	private static final Comparator<PlayingCard> SORT_BY_RANK = new RankComparator();
	private static final Comparator<PlayingCard> SORT_BY_SUIT = new SuitComparator();
	
	protected void populate() {
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				add(new PlayingCard(rank, suit));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public PlayingCardDeck sortByRankAndSuit() {
		return sortBy(SORT_BY_RANK, SORT_BY_SUIT);
	}

	@SuppressWarnings("unchecked")
	public PlayingCardDeck sortBySuitAndRank() {
		return sortBy(SORT_BY_SUIT, SORT_BY_RANK);
	}
	
	public PlayingCardDeck sortBySuit() {
		return sortBy(SORT_BY_SUIT);
	}
	
	public PlayingCardDeck sortByRank() {
		return sortBy(SORT_BY_RANK);
	}
}
