package game.playingcard;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game.core.collections.Function;
import game.core.collections.Predicate;
import game.core.components.Hand;
import game.core.util.CollectionUtil;
import game.playingcard.attribute.Rank;
import game.playingcard.attribute.Suit;
import game.playingcard.condition.Rating;
import game.playingcard.group.GroupByRank;
import game.playingcard.group.GroupBySuit;

public class PlayingCardHand extends Hand<PlayingCard> {
	private static final Function<PlayingCard, Rank> GROUP_BY_RANK = new GroupByRank();
	private static final Function<PlayingCard, Suit> GROUP_BY_SUIT = new GroupBySuit();
	
	private int maxSize;

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public PlayingCardHand() {
		this(5);
	}

	public PlayingCardHand(int maxSize) {
		super();

		this.maxSize = maxSize;
	}

	public Rating getCondition() {
		if (!hasCards()) {
			return Rating.NOTHING;
		}

		Map<Rank, List<PlayingCard>> count = groupByRank();

		if (hasFlush() && hasStright()) {
			if (hasRank(Rank.ACE)) {
				return Rating.ROYAL_FLUSH;
			}

			return Rating.STRAIGHT_FLUSH;
		}

		if (hasFourOfAKind(count)) {
			return Rating.FOUR_OF_A_KIND;
		}

		if (hasFullHouse(count)) {
			return Rating.FULL_HOUSE;
		}

		if (hasFlush()) {
			return Rating.FLUSH;
		}

		if (hasStright()) {
			return Rating.STRAIGHT;
		}

		if (hasThreeOfAKind(count)) {
			return Rating.THREE_OF_A_KIND;
		}

		if (hasTwoPair(count)) {
			return Rating.TWO_PAIR;
		}

		if (hasPair(count)) {
			return Rating.PAIR;
		}

		return Rating.HIGH_CARD;
	}

	public boolean hasRank(Rank rank) {
		return CollectionUtil.some(getCards(), new Predicate<PlayingCard>() {
			@Override
			public boolean filter(PlayingCard card) {
				return card.getRank().equals(rank);
			}
		});
	}

	public boolean hasFourOfAKind(Map<Rank, List<PlayingCard>> count) {
		for (Entry<Rank, List<PlayingCard>> entry : count.entrySet()) {
			if (entry.getValue().size() == 4) {
				return true;
			}
		}

		return false;
	}

	public boolean hasFullHouse(Map<Rank, List<PlayingCard>> count) {
		return hasThreeOfAKind(count) && hasPair(count);
	}

	public boolean hasFlush() {
		if (size() < getMaxSize()) {
			return false;
		}

		Iterator<PlayingCard> it = getCards().iterator();
		Suit suit = it.next().getSuit();

		while (it.hasNext()) {
			if (!it.next().getSuit().equals(suit)) {
				return false;
			}
		}

		return true;
	}

	public boolean hasStright() {
		if (size() < getMaxSize()) {
			return false;
		}

		Iterator<PlayingCard> it = getCards().iterator();
		Rank prev = it.next().getRank();

		while (it.hasNext()) {
			Rank curr = it.next().getRank();
			if (Math.abs(curr.getWeight() - prev.getWeight()) > 1) {
				return false;
			}
			prev = curr;
		}

		return true;
	}

	public boolean hasThreeOfAKind(Map<Rank, List<PlayingCard>> count) {
		for (Entry<Rank, List<PlayingCard>> entry : count.entrySet()) {
			if (entry.getValue().size() == 3) {
				return true;
			}
		}

		return false;
	}

	public boolean hasTwoPair(Map<Rank, List<PlayingCard>> count) {
		return countPairs(count) == 2;
	}

	public boolean hasPair(Map<Rank, List<PlayingCard>> count) {
		return countPairs(count) == 1;
	}

	public int countPairs(Map<Rank, List<PlayingCard>> count) {
		int n = 0;

		for (Entry<Rank, List<PlayingCard>> entry : count.entrySet()) {
			if (entry.getValue().size() == 2) {
				n++;
			}
		}

		return n;
	}

	public Map<Rank, List<PlayingCard>> groupByRank() {
		return groupBy(this, GROUP_BY_RANK);
	}

	public Map<Suit, List<PlayingCard>> groupBySuit() {
		return groupBy(this, GROUP_BY_SUIT);
	}

	public Map<Integer, List<Rank>> frequencyByRank() {
		return frequency(this, GROUP_BY_RANK);
	}
}
