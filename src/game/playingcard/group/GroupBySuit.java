package game.playingcard.group;

import game.core.collections.Function;
import game.playingcard.PlayingCard;
import game.playingcard.attribute.Suit;

public class GroupBySuit implements Function<PlayingCard, Suit> {
	@Override
	public Suit apply(PlayingCard card) {
		return card.getSuit();
	}
}