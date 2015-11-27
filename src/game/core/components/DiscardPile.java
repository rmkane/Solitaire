package game.core.components;

import java.util.Collection;
import java.util.Stack;

import game.core.Card;
import game.core.CardHolder;
import game.core.util.CollectionUtil;

public class DiscardPile<CARD extends Card<CARD>> implements CardHolder<Stack<CARD>, CARD> {
	private Stack<CARD> cards;
	
	@Override
	public Stack<CARD> getCards() {
		return cards;
	}

	@Override
	public void setCards(Stack<CARD> cards) {
		this.cards = cards;
	}
	
	public DiscardPile() {
		this(new Stack<CARD>());
	}

	public DiscardPile(Stack<CARD> cards) {
		this.cards = cards;
	}

	@Override
	public boolean add(CARD card) {
		return cards.add(card);
	}

	@Override
	public boolean addAll(Collection<CARD> cards) {
		return cards.addAll(cards);
	}
	
	@Override
	public CARD remove() {
		return cards.remove(cards.size() - 1);
	}

	@Override
	public int size() {
		return cards.size();
	}
	
	@Override
	public boolean hasCards() {
		return !cards.isEmpty();
	}

	@Override
	public boolean discard(CARD target, CardHolder<Stack<CARD>, CARD> destination) {
		return CollectionUtil.move(cards, destination.getCards(), target);
	}

	@Override
	public <CARDS extends Collection<CARD>> void discardAll(CardHolder<CARDS, CARD> destination) {
		CollectionUtil.moveAll(cards, destination.getCards());
	}
}
