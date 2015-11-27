package game.playingcard.comparator;

import java.util.Comparator;

import game.playingcard.PlayingCard;

public class SuitComparator implements Comparator<PlayingCard> {
	@Override
	public int compare(PlayingCard card1, PlayingCard card2) {
		return card1.getSuit().getSymbol() - card2.getSuit().getSymbol();
	}
}
