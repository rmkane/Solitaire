package game;
import game.card.Deck;

public class PlayingCardDeck extends Deck<PlayingCard> {
	protected void populate() {
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				add(new PlayingCard(rank, suit));
			}
		}
	}
}
