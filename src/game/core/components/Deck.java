package game.core.components;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import game.core.Card;
import game.core.CardHolder;
import game.core.collections.ChainedComparator;
import game.core.util.CollectionUtil;

public abstract class Deck<CARD extends Card<CARD>> implements CardHolder<Stack<CARD>, CARD> {
	private Stack<CARD> cards;

	@Override
	public Stack<CARD> getCards() {
		return cards;
	}

	@Override
	public void setCards(Stack<CARD> cards) {
		this.cards = cards;
	}

	public Deck() {
		this(new Stack<CARD>());
	}

	public Deck(Stack<CARD> cards) {
		this.cards = cards;

		populate();
		shuffle();
	}

	protected abstract void populate();

	@SuppressWarnings("unchecked")
	public <DECK extends CardHolder<Stack<CARD>, CARD>> DECK shuffle() {
		Random rnd = ThreadLocalRandom.current(); // new Random() if JDK6

		for (int i = cards.size() - 1; i > 0; i--) {
			Collections.swap(cards, rnd.nextInt(i + 1), i);
		}

		return (DECK) this;
	}

	@SuppressWarnings("unchecked")
	public <DECK extends CardHolder<Stack<CARD>, CARD>> DECK sort() {
		Collections.sort(cards);

		return (DECK) this;
	}

	@SuppressWarnings("unchecked")
	public <DECK extends CardHolder<Stack<CARD>, CARD>> DECK sortBy(Comparator<CARD> comparator) {
		Collections.sort(cards, comparator);

		return (DECK) this;
	}

	@SuppressWarnings("unchecked")
	public <DECK extends CardHolder<Stack<CARD>, CARD>> DECK sortBy(Comparator<CARD>... comparators) {
		return sortBy(Arrays.asList(comparators));
	}

	@SuppressWarnings("unchecked")
	public <DECK extends CardHolder<Stack<CARD>, CARD>> DECK sortBy(List<Comparator<CARD>> comparators) {
		Collections.sort(cards, new ChainedComparator<CARD>(comparators));

		return (DECK) this;
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

	public boolean deal(Hand<CARD> destination) {
		return CollectionUtil.moveLast(cards, destination.getCards());
	}
}
