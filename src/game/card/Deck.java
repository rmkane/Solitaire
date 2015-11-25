package game.card;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Deck<T extends Card<T>> implements CardHolder<T> {
	private List<T> cards;

	@Override
	public List<T> getCards() {
		return cards;
	}

	@Override
	public void setCards(List<T> cards) {
		this.cards = cards;
	}

	public Deck() {
		this(new LinkedList<T>());
	}

	public Deck(List<T> cards) {
		this.cards = cards;

		populate();
		shuffle();
	}

	protected abstract void populate();

	@SuppressWarnings("unchecked")
	public <U extends CardHolder<T>> U shuffle() {
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random rnd = ThreadLocalRandom.current();

		for (int i = cards.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);

			Collections.swap(cards, index, i);
		}

		return (U) this;
	}

	@SuppressWarnings("unchecked")
	public <U extends CardHolder<T>> U sort() {
		Collections.sort(cards);

		return (U) this;
	}

	@Override
	public boolean hasCards() {
		return !cards.isEmpty();
	}

	@Override
	public boolean add(T card) {
		return cards.add(card);
	}

	@Override
	public T remove() {
		return cards.remove(cards.size() - 1);
	}

	@Override
	public boolean discard(T target, Deck<T> destination) {
		boolean present = cards.remove(target);
		
		if (!present) {
			return false;
		}

		return destination.add(target);
	}
}
