import game.PlayingCardDeck;
import game.card.Card;
import game.card.CardHolder;

public class PlayingCardTest {
	public static void main(String[] args) {
		CardHolder<? extends Card<?>> deck = new PlayingCardDeck().sort();
		
		
		int i = 1;
		while (deck.hasCards()) {
			System.out.printf("%2d.  %s%n", i++, deck.remove().formattedName());
		}
	}
}
