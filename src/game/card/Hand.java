package game.card;

import java.util.List;

public class Hand<T extends Card<T>> implements CardHolder<T> {
	private List<T> cards;
	
	@Override
	public List<T> getCards() {
		return cards;
	}

	@Override
	public void setCards(List<T> cards) {
		this.cards = cards;
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
