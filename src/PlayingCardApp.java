import game.core.components.DiscardPile;
import game.playingcard.PlayingCard;
import game.playingcard.PlayingCardDeck;
import game.playingcard.PlayingCardHand;

public class PlayingCardApp {
	public static void main(String[] args) {
		testDepleet();
		//printRemove();
	}

	public static void testDepleet() {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardHand hand = new PlayingCardHand();
		DiscardPile<PlayingCard> discard = new DiscardPile<PlayingCard>();
		int drawMax = 5;
		
		while (deck.hasCards()) {
			for (int i = 0; i < drawMax; i++) {
				deck.deal(hand);
			}
			
			//String cond = hand.getCondition().formattedName(hand);
			String cond = hand.getCondition().getFormatter().format(hand);
			
			System.out.println(String.format("Hand --> %s %s", hand, cond));
			System.out.printf("%nSize --> Hand: %d | Deck: %d | Discard: %d%n%n%n", hand.size(), deck.size(), discard.size());
			
			hand.discardAll(discard);
		}
	}

	public static void printRemove() {
		PlayingCardDeck deck = new PlayingCardDeck().sortByRankAndSuit();
		int i = 1;

		while (deck.hasCards()) {
			System.out.printf("%s%s", deck.remove().formattedName(), i++ % 4 == 0 ? "\n" : "\t");
		}
	}
}
