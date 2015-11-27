package game.playingcard.formatter;

import java.util.List;
import java.util.Map;

import game.core.formatter.HandFormatter;
import game.playingcard.PlayingCard;
import game.playingcard.PlayingCardHand;
import game.playingcard.attribute.Rank;

public abstract class PlayingCardHandFormatter implements HandFormatter<PlayingCardHand, PlayingCard> {
	public Map<Integer, List<Rank>> getFrequency(PlayingCardHand hand) {
		return hand.frequencyByRank();
	}

	public String lookup(Map<Integer, List<Rank>> frequency, int index, int position) {
		return frequency.get(index).get(position).formattedName();
	}

	public String highCard(PlayingCardHand hand) {
		return hand.max().getRank().formattedName();
	}
}
