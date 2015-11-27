package game.playingcard.group;

import game.core.collections.Function;
import game.playingcard.PlayingCard;
import game.playingcard.attribute.Rank;

public class GroupByRank implements Function<PlayingCard, Rank> {
	@Override
	public Rank apply(PlayingCard card) {
		return card.getRank();
	}
}